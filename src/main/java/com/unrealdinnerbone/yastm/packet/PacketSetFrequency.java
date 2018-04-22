package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yastm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import com.unrealdinnerbone.yaum.api.network.ISimplePacket;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import com.unrealdinnerbone.yaum.libs.utils.NoNullUtil;
import com.unrealdinnerbone.yaum.libs.utils.RegistryUtils;
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
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PacketSetFrequency implements ISimplePacket<PacketSetFrequency> {


    private DimBlockPos blockPos;
    private int ID;
    private String effectID;
    private String particleID;

    public PacketSetFrequency() {

    }

    public PacketSetFrequency(DimBlockPos pos, int id, String effectID, String particleID) {
        this.ID = id;
        this.blockPos = pos;
        this.effectID = effectID;
        this.particleID = particleID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());
        ID = buf.readInt();
        int effectL = buf.readInt();
        effectID = buf.readCharSequence(effectL, StandardCharsets.UTF_8).toString();
        int partucleL = buf.readInt();
        particleID = buf.readCharSequence(partucleL, StandardCharsets.UTF_8).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(blockPos.getDimID());
        buf.writeInt(ID);
        buf.writeInt(effectID.length());
        buf.writeCharSequence(effectID, StandardCharsets.UTF_8);
        buf.writeInt(particleID.length());
        buf.writeCharSequence(particleID, StandardCharsets.UTF_8);

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

    private void handle(PacketSetFrequency message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().player;
        World world = player.getEntityWorld();
        if (world.isBlockLoaded(message.getBlockPos())) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(message.getBlockPos()));
            if (tileEntity instanceof TileEntityTeleporter) {
                YatmWorldSaveData yatmWorldSaveData = YatmWorldSaveData.get(world);
                yatmWorldSaveData.removeDimBlockPos(message.getBlockPos());
                yatmWorldSaveData.addTeleporter(message.getID(), message.getBlockPos());
                yatmWorldSaveData.save(world);
                TileEntityTeleporter telporter = (TileEntityTeleporter) tileEntity;
                telporter.setTelerporterEffect(NoNullUtil.getObjectOrElseNotNull(RegistryUtils.getRegistryObjectFormName(YastmRegistries.getFrequencyRegistry(), message.getEffectID()), telporter.getTelerporterEffect()));
                telporter.setTeleporterParticleEffect(NoNullUtil.getObjectOrElseNotNull(RegistryUtils.getRegistryObjectFormName(YastmRegistries.getParticleEffectsRegistry(), message.getParticleID()), telporter.getTeleporterParticleEffect()));
            }
        }
    }

    @Override
    public IMessage onMessage(PacketSetFrequency message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
        return null;
    }
}
