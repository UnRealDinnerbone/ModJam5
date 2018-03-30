package com.unrealdinnerbone.yatm.data;

import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class YatmWorldSaveData extends WorldSavedData {


    private static final String DATA_NAME = Reference.MOD_ID + "_TelerporterData";

    public YatmWorldSaveData() {
        super(DATA_NAME);
    }
    public YatmWorldSaveData(String s) {
        super(s);
    }

    public static YatmWorldSaveData get(World world) {
        // The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
        MapStorage storage = IS_GLOBAL ? world.getMapStorage() : world.getPerWorldStorage();
        ExampleWorldSavedData instance = (ExampleWorldSavedData) storage.getOrLoadData(ExampleWorldSavedData.class, DATA_NAME);

        if (instance == null) {
            instance = new ExampleWorldSavedData();
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
