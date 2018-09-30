package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.lib.util.RegistryUtils;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.lang3.ObjectUtils;

import java.nio.charset.StandardCharsets;

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
        buf.writeLong(blockPos.getBlockPos().toLong());
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

    @Override
    public void handlePacket(PacketSetFrequency message, MessageContext ctx) {
        World world = ctx.getServerHandler().player.getEntityWorld();
        if (world.isBlockLoaded(message.getBlockPos().getBlockPos())) {
            TileEntity tileEntity = world.getTileEntity(message.getBlockPos().getBlockPos());
            if (tileEntity instanceof TileEntityTeleporter) {
                YatmWorldSaveData yatmWorldSaveData = YatmWorldSaveData.get(world);
                yatmWorldSaveData.getTelerporterData().removeTeleporter(message.getBlockPos());
                yatmWorldSaveData.getTelerporterData().addTeleporter(message.getID(), message.getBlockPos());
                yatmWorldSaveData.save(world);
                TileEntityTeleporter telporter = (TileEntityTeleporter) tileEntity;
                telporter.setTelerporterEffect(ObjectUtils.defaultIfNull(RegistryUtils.getRegistryObjectFormName(YastmRegistries.getFrequencyRegistry(), new ResourceLocation(message.getEffectID())), telporter.getTelerporterEffect()));
                telporter.setTeleporterParticleEffect(ObjectUtils.defaultIfNull(RegistryUtils.getRegistryObjectFormName(YastmRegistries.getParticleEffectsRegistry(), new ResourceLocation(message.getParticleID())), telporter.getTeleporterParticleEffect()));
            }
        }
    }
}
