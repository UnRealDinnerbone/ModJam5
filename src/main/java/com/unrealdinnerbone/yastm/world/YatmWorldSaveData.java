package com.unrealdinnerbone.yastm.world;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.lib.TelerporterData;
import com.unrealdinnerbone.yaum.lib.DimBlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

public class YatmWorldSaveData extends WorldSavedData {


    private static final String DATA_NAME = Yastm.MOD_ID + "_teleporter";

    private TelerporterData telerporterData;

    public YatmWorldSaveData() {
        super(DATA_NAME);
        this.telerporterData = new TelerporterData();
    }

    public YatmWorldSaveData(String s) {
        super(s);
        this.telerporterData = new TelerporterData();
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
    public void readFromNBT(NBTTagCompound compound) {
        telerporterData.initOrClear();
        if(compound.hasKey("tell")) {
            NBTTagList bigTagList = compound.getTagList("tell", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < bigTagList.tagCount(); i++) {
                NBTTagCompound nbtTagCompound = bigTagList.getCompoundTagAt(i);
                int tellID = nbtTagCompound.getInteger("id");
                if(!telerporterData.hasID(tellID)) {
                    telerporterData.initIDIfEmpty(tellID);
                }
                NBTTagList tells = nbtTagCompound.getTagList("teleporters", Constants.NBT.TAG_COMPOUND);
                for (int x = 0; x < tells.tagCount(); x++) {
                    DimBlockPos dimBlockPos = DimBlockPos.fromTagCompound(tells.getCompoundTagAt(x));
                    System.out.println(dimBlockPos + " loop");
                    telerporterData.addTeleporter(tellID, DimBlockPos.fromTagCompound(tells.getCompoundTagAt(x)));
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
        NBTTagList tagList = new NBTTagList();
        for(int telerpotedID: telerporterData.getKeySet()) {
            NBTTagCompound telerpoterDataComppound = new NBTTagCompound();
            NBTTagList dimBlockPosLTagist = new NBTTagList();
            for (DimBlockPos dimBlockPos : telerporterData.getBlockPosFromId(telerpotedID)) {
                dimBlockPosLTagist.appendTag(dimBlockPos.toTagCompound());
            }
            telerpoterDataComppound.setInteger("id", telerpotedID);
            telerpoterDataComppound.setTag("teleporters", dimBlockPosLTagist);
            tagList.appendTag(telerpoterDataComppound);
        }
        compound.setTag("tell", tagList);
        return compound;
    }

    public TelerporterData getTelerporterData() {
        return telerporterData;
    }
}
