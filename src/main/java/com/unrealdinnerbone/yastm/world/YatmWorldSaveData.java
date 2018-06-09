package com.unrealdinnerbone.yastm.world;

import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nullable;
import java.util.*;

public class YatmWorldSaveData extends WorldSavedData {


    private static final String DATA_NAME = Reference.MOD_ID + "_TeleporterData";
    private HashMap<Integer, List<DimBlockPos>> positions;

    public YatmWorldSaveData() {
        super(DATA_NAME);
        this.positions = new HashMap<>();
    }

    public YatmWorldSaveData(String s) {
        super(s);
        this.positions = new HashMap<>();
    }


    public void addTeleporter(int id, DimBlockPos blockPos) {
        if(blockPos != null) {
            if(!this.positions.containsKey(id) && id != 0) {
                this.positions.put(id, new ArrayList<>());
            }
            if(id != 0 && !this.positions.get(id).contains(blockPos))  {
                this.positions.get(id).add(blockPos);
            }
        }
    }

    public List<DimBlockPos> getPostionsFormID(int id) {
        if(this.positions.containsKey(id) && id != 0) {
            return this.positions.get(id);
        }else {
            return new ArrayList<>();
        }
    }

    public int getIDFormPos(DimBlockPos pos) {
        for(int key: positions.keySet()) {
            for(DimBlockPos dimBlockPos : positions.get(key)) {
                if(dimBlockPos.equals(pos)) {
                    return key;
                }
            }
        }
        return 0;
    }

    public boolean hasTwin(DimBlockPos blockPos) {
        int ID = getIDFormPos(blockPos);
        if(positions.containsKey(ID)) {
            List<DimBlockPos> posList = positions.get(ID);
            if(posList.size() == 2) {
                List<DimBlockPos> dummyList = new ArrayList<>(posList);
                List<DimBlockPos> toRemove = new ArrayList<>();
                for (DimBlockPos pos : posList) {
                    if (blockPos.equals(pos)) {
                        toRemove.add(pos);
                    }
                }
                dummyList.removeAll(toRemove);
                return dummyList.size() == 1;
            }
        }
        return false;
    }

    public void removeDimBlockPos(DimBlockPos blockPos) {
        if(hasDimBlockPos(blockPos)) {
            for(int key: positions.keySet()) {
                List<DimBlockPos> posList = positions.get(key);
                if(posList.contains(blockPos)) {
                    posList.remove(blockPos);
                    return;
                }
            }
        }
    }

    public boolean hasDimBlockPos(DimBlockPos blockPos) {
        for (List<DimBlockPos> dimBlockPos : positions.values()) {
            for (DimBlockPos dimBlockPo : dimBlockPos) {
                if (blockPos.equals(dimBlockPo)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public DimBlockPos getTwin(DimBlockPos pos) {
        int id = getIDFormPos(pos);
        if(hasTwin(pos)) {
            List<DimBlockPos> blockPosList = new ArrayList<>(positions.get(id));
            blockPosList.remove(pos);
            return blockPosList.stream().findFirst().get();
        }else {
            return null;
        }
    }

    public void removePostionFormID(int id, DimBlockPos pos) {
        if(this.positions.containsKey(id)) {
            this.positions.get(id).remove(pos);
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
        positions = new HashMap<>();
        for(String key: nbt.getKeySet()) {
            if(key.startsWith("tpNumber_")) {
                NBTTagCompound tagCompound = nbt.getCompoundTag(key);
                if(tagCompound.getKeySet().size() > 0) {
                    List<DimBlockPos> posList = new ArrayList<>();
                    for(String key2: tagCompound.getKeySet()) {
                        String  number = tagCompound.getString(key2);
                        posList.add(DimBlockPos.fromStoreString(number));
                    }
                    String number = key.replace("tpNumber_", "");
                    int x = Integer.parseInt(number);
                    positions.put(x, posList);
                }
            }

        }
    }

    public void save(World world) {
        world.setData(DATA_NAME, this);
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        for(int key: positions.keySet()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            int count = 0;
            for(DimBlockPos pos: positions.get(key)) {
                tagCompound.setString("" + count++, pos.toStoreString());
            }
            compound.setTag("tpNumber_" + key, tagCompound);
        }
        return compound;
    }
}
