package com.unrealdinnerbone.yatm.world;

import com.unrealdinnerbone.yatm.lib.DoubleStoreObject;
import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.List;

public class YatmWorldSaveData extends WorldSavedData {


    private static final String DATA_NAME = Reference.MOD_ID + "_TelerporterData";

    public YatmWorldSaveData() {
        super(DATA_NAME);
    }
    public YatmWorldSaveData(String s) {
        super(s);
    }

    List<DoubleStoreObject<BlockPos, Integer>> doubleStoreObjects;




    public static YatmWorldSaveData get(World world) {
        MapStorage storage = world.getMapStorage();
        YatmWorldSaveData instance = (YatmWorldSaveData) storage.getOrLoadData(YatmWorldSaveData.class, DATA_NAME);
        if (instance == null) {
            instance = new YatmWorldSaveData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return null;
    }
}
