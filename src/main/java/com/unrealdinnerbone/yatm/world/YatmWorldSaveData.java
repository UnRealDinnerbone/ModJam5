package com.unrealdinnerbone.yatm.world;

import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YatmWorldSaveData extends WorldSavedData {


    private static final String DATA_NAME = Reference.MOD_ID + "_TelerporterData";
    private HashMap<Integer, List<BlockPos>> postions;

    public YatmWorldSaveData() {
        super(DATA_NAME);
        this.postions = new HashMap<>();
    }

    public YatmWorldSaveData(String s) {
        super(s);
        this.postions = new HashMap<>();
    }


    public void addTelporter(int id, BlockPos blockPos) {
        if(!this.postions.containsKey(id) && id != 0) {
            this.postions.put(id, new ArrayList<>());
        }
        if(!this.postions.get(id).contains(blockPos) && id != 0)  {
            this.postions.get(id).add(blockPos);
        }
    }

    public List<BlockPos> getPostionsFormID(int id) {
        if(this.postions.containsKey(id) && id != 0) {
            return this.postions.get(id);
        }else {
            return new ArrayList<>();
        }
    }

    public void removePostionFormID(int id, BlockPos pos) {
        if(this.postions.containsKey(id)) {
            this.postions.get(id).remove(pos);
        }
    }



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

        for(String key: nbt.getKeySet()) {
            NBTTagCompound tagCompound = nbt.getCompoundTag(key);
           //Todo

        }
    }

    public void save(World world) {
        world.setData(DATA_NAME, this);
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        for(int key: postions.keySet()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            int count = 0;
            for(BlockPos pos: postions.get(key)) {
                tagCompound.setLong("" + count++, pos.toLong());
            }
            compound.setTag("" + key, tagCompound);
        }
        return compound;
    }
}
