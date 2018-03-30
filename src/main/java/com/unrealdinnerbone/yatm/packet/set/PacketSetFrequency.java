package com.unrealdinnerbone.yatm.packet.set;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketSetFrequency implements IMessage {

    private BlockPos blockPos;
    private int ID;

    public PacketSetFrequency() {

    }

    public PacketSetFrequency(BlockPos pos, int id) {
        this.ID = id;
        this.blockPos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
        ID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(ID);
    }

    public int getID() {
        return ID;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

}
