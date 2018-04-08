package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yastm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import com.unrealdinnerbone.yaum.api.network.ISimplePacket;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;
import java.util.Map;

public class PacketSetFrequency implements ISimplePacket<PacketSetFrequency> {

    private BlockPos startPos;
    private int startID;
    private TelerporterEffect startEffect;

    private DimBlockPos blockPos;
    private int ID;
    private TelerporterEffect effect;

    public PacketSetFrequency() {

    }

    public PacketSetFrequency(DimBlockPos pos, int id, BlockPos startPos, int startID, TelerporterEffect effect, TelerporterEffect startEffect) {
        this.ID = id;
        this.blockPos = pos;
        this.startID = startID;
        this.startPos = startPos;
        this.effect = effect;
        this.startEffect = startEffect;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());
        ID = buf.readInt();
        startPos = BlockPos.fromLong(buf.readLong());
        startID = buf.readInt();
        int l = buf.readInt();
        CharSequence name = buf.readCharSequence(l, Charset.forName("utf-8"));
        for(Map.Entry<ResourceLocation, TelerporterEffect> entry : EventRegisterRegisters.getFrequencyRegistry().getEntries()) {
            if(entry.getValue().getRegistryName().toString().equalsIgnoreCase(name.toString())) {
                effect = entry.getValue();
                break;
            }
        }
        int ll = buf.readInt();
        CharSequence name2 = buf.readCharSequence(ll, Charset.forName("utf-8"));
        for(Map.Entry<ResourceLocation, TelerporterEffect> entry : EventRegisterRegisters.getFrequencyRegistry().getEntries()) {
            if(entry.getValue().getRegistryName().toString().equalsIgnoreCase(name2.toString())) {
                startEffect = entry.getValue();
                break;
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(blockPos.getDimID());
        buf.writeInt(ID);
        buf.writeLong(startPos.toLong());
        buf.writeInt(startID);
        buf.writeInt(effect.getRegistryName().toString().length());
        buf.writeCharSequence(effect.getRegistryName().toString(), Charset.forName("utf-8"));

        buf.writeInt(startEffect.getRegistryName().toString().length());
        buf.writeCharSequence(startEffect.getRegistryName().toString(), Charset.forName("utf-8"));
    }

    public int getID() {
        return ID;
    }

    public DimBlockPos getBlockPos() {
        return blockPos;
    }

    public BlockPos getStartPos() {
        return startPos;
    }

    public int getStartID() {
        return startID;
    }

    public TelerporterEffect getEffect() {
        return effect;
    }

    public TelerporterEffect getStartEffect() {
        return startEffect;
    }

    private void handle(PacketSetFrequency message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().player;
        World world = player.getEntityWorld();
        if (world.isBlockLoaded(message.getBlockPos())) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(message.getBlockPos()));
            if (tileEntity instanceof TileEntityTeleporter) {
                if(message.getStartID() != message.getID()) {
                    YatmWorldSaveData yatmWorldSaveData = YatmWorldSaveData.get(world);
                    yatmWorldSaveData.removeDimBlockPos(message.getBlockPos());
                    yatmWorldSaveData.addTelporter(message.getID(), message.getBlockPos());
                    yatmWorldSaveData.save(world);
                }
                TileEntityTeleporter telporter = (TileEntityTeleporter) tileEntity;
                telporter.setFrequencyEffect(message.getEffect());
            }
        }
    }

    @Override
    public IMessage onMessage(PacketSetFrequency message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
        return null;
    }
}
