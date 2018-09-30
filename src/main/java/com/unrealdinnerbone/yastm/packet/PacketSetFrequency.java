package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;

public class PacketSetFrequency implements ISimplePacket<PacketSetFrequency> {


    private DimBlockPos blockPos;
    private int ID;
    private String effectID;
    private String particleID;

    public PacketSetFrequency() {

    }

    public PacketSetFrequency(DimBlockPos pos, int id) {
        this.ID = id;
        this.blockPos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());
        ID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.getBlockPos().toLong());
        buf.writeInt(blockPos.getDimID());
        buf.writeInt(ID);

    }

    public int getID() {
        return ID;
    }

    public DimBlockPos getBlockPos() {
        return blockPos;
    }

    public String getEffectID() {
        return effectID;
    }

    public String getParticleID() {
        return particleID;
    }

    @Override
    public IMessage onMessage(PacketSetFrequency message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
            World world = ctx.getServerHandler().player.getEntityWorld();
            if (world.isBlockLoaded(message.getBlockPos().getBlockPos())) {
                TileEntity tileEntity = world.getTileEntity(message.getBlockPos().getBlockPos());
                if (tileEntity instanceof TileEntityTeleporter) {
                    YatmWorldSaveData yatmWorldSaveData = YatmWorldSaveData.get(world);
                    yatmWorldSaveData.getTelerporterData().removeTeleporter(message.getBlockPos());
                    yatmWorldSaveData.getTelerporterData().addTeleporter(message.getID(), message.getBlockPos());
                    yatmWorldSaveData.save(world);
                }
            }
        });
        return null;
    }

}
