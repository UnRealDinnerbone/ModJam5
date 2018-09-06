package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class PacketSetFrequency implements IMessage {


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

}
