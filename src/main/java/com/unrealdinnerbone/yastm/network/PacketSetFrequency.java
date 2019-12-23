package com.unrealdinnerbone.yastm.network;

import net.minecraft.util.math.BlockPos;

public class PacketSetFrequency
{

    private final BlockPos blockPos;
    private final int newId;

    public PacketSetFrequency(BlockPos pos, int newId) {
        this.blockPos = pos;
        this.newId = newId;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public int getNewId() {
        return newId;
    }
}
