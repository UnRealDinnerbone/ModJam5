package com.unrealdinnerbone.yastm.lib;

import com.google.common.base.MoreObjects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class DimBlockPos {

    private int dimID;
    private BlockPos blockPos;

    public DimBlockPos(BlockPos blockPos, int dimID) {
        this.dimID = dimID;
        this.blockPos = blockPos.toImmutable();
    }


    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public int getDimID() {
        return dimID;
    }

    public void setDimID(int dimID) {
        this.dimID = dimID;
    }


    @Override
    public boolean equals(Object object) {
        if(object instanceof DimBlockPos) {
            DimBlockPos dimBlockPos = (DimBlockPos) object;
            if (this.dimID == dimBlockPos.dimID) {
                return blockPos.equals(dimBlockPos.blockPos);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("dimID", dimID).add("x", blockPos.getX()).add("y", blockPos.getY()).add("z", blockPos.getZ()).toString();
    }

    public NBTTagCompound toTagCompound() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setInteger("x", this.blockPos.getX());
        nbttagcompound.setInteger("y", this.blockPos.getY());
        nbttagcompound.setInteger("z", this.blockPos.getZ());
        nbttagcompound.setInteger("d", dimID);
        return nbttagcompound;
    }

    public static DimBlockPos fromTagCompound(NBTTagCompound tagCompound) {
        int x = tagCompound.getInteger("x");
        int y = tagCompound.getInteger("y");
        int z = tagCompound.getInteger("z");
        int dimID = tagCompound.getInteger("d");
        return new DimBlockPos(new BlockPos(x, y,z ), dimID);

    }
}
