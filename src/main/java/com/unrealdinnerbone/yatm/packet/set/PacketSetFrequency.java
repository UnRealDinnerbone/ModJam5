package com.unrealdinnerbone.yatm.packet.set;

import io.netty.buffer.ByteBuf;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketSetFrequency implements IMessage {

    private BlockPos blockPos;
    private int ID;
    private BlockPos startPos;
    private int startID;

    public PacketSetFrequency() {

    }

    public PacketSetFrequency(BlockPos pos, int id, BlockPos startPos, int startID) {
        this.ID = id;
        this.blockPos = pos;
        this.startID = startID;
        this.startPos = startPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
        ID = buf.readInt();
        startPos = BlockPos.fromLong(buf.readLong());
        startID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(ID);
        buf.writeLong(startPos.toLong());
        buf.writeInt(startID);
    }

    public int getID() {
        return ID;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockPos getStartPos() {
        return startPos;
    }

    public int getStartID() {
        return startID;
    }
}
