package com.unrealdinnerbone.yatm.world;

import com.unrealdinnerbone.yatm.lib.Reference;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.Collection;
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
        if(blockPos != null) {
            if(!this.postions.containsKey(id) && id != 0) {
                this.postions.put(id, new ArrayList<>());
            }
            if(id != 0 && !this.postions.get(id).contains(blockPos))  {
                this.postions.get(id).add(blockPos);
                System.out.println("Added BlockPos " + blockPos + " with id" + id);
            }
        }
    }

    public List<BlockPos> getPostionsFormID(int id) {
        if(this.postions.containsKey(id) && id != 0) {
            return this.postions.get(id);
        }else {
            return new ArrayList<>();
        }
    }

    public int removeBlockPos(BlockPos blockPos) {
        if(hasBlockPos(blockPos)) {
            for(int key: postions.keySet()) {
                List<BlockPos> posList = postions.get(key);
                if(posList.contains(blockPos)) {
                    posList.remove(blockPos);
                    System.out.println("Removed BlockPos " + blockPos + " form id" + key);
                    return key;
                }
            }
        }
        return 0;
    }

    public boolean hasBlockPos(BlockPos blockPos) {
        return postions.values().stream().flatMap(Collection::stream).anyMatch(blockPos1 -> blockPos.toLong() == blockPos1.toLong());
    }

    public BlockPos getOtherPosFormIdAndPos(int id, BlockPos pos) {
        List<BlockPos> posList = getPostionsFormID(id);
        for (BlockPos pos1 : posList) {
            if (pos1.toLong() != pos.toLong()) {
                return pos1;
            }
        }
        return null;
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
        postions = new HashMap<>();
        for(String key: nbt.getKeySet()) {
            if(key.startsWith("tpNumber_")) {
                NBTTagCompound tagCompound = nbt.getCompoundTag(key);
                List<BlockPos> posList = new ArrayList<>();
                for(String key2: tagCompound.getKeySet()) {
                    long number = tagCompound.getLong(key2);
                    posList.add(BlockPos.fromLong(number));
                }
                String number = key.replace("tpNumber_", "");
                int x = Integer.parseInt(number);
                postions.put(x, posList);
            }

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
            compound.setTag("tpNumber_" + key, tagCompound);
        }
        return compound;
    }
}
