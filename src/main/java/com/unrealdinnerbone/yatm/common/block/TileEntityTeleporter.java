package com.unrealdinnerbone.yatm.common.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTeleporter extends TileEntity {

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setInteger("id", ID);
        compound.setTag("yatm", tagCompound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        NBTTagCompound tagCompound = (NBTTagCompound) compound.getTag("yatm");
        this.ID = tagCompound.getInteger("id");
        super.readFromNBT(compound);
    }
}
