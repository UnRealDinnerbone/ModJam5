package com.unrealdinnerbone.yastm.world;

import com.unrealdinnerbone.yastm.Yastm;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;
import java.util.stream.Collectors;

public class YastmWorldSaveData extends WorldSavedData {

    public static final String NAME = Yastm.MOD_ID + "_" + "teleporters";

    private Map<Integer, List<BlockPos>> telporters;

    private YastmWorldSaveData() {
        super(NAME);
        telporters = new HashMap<>();
    }

    @Override
    public void read(CompoundNBT nbt) {
        HashMap<Integer, List<BlockPos>> pos = new HashMap<>();
        for (String intKey : nbt.keySet()) {
            Integer id = Integer.valueOf(intKey);
            List<BlockPos> blockPos = Arrays.stream(nbt.getLongArray(intKey)).mapToObj(BlockPos::fromLong).collect(Collectors.toList());
            pos.put(id, blockPos);
        }
        telporters = pos;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        telporters.keySet().forEach(integer -> compound.put(integer.toString(), new LongArrayNBT(telporters.get(integer).stream().map(BlockPos::toLong).collect(Collectors.toList()))));
        return compound;
    }

    public boolean addTeleporter(int id, BlockPos blockPos) {
        if(!this.telporters.containsKey(id)) {
            this.telporters.put(id, new ArrayList<>());
        }
        if (this.telporters.get(id).size() < 2) {
            this.telporters.get(id).add(blockPos);
            markDirty();
            return true;
        }
        return false;
    }

    public boolean removeTeleporter(int id, BlockPos blockPos) {
        if(this.telporters.containsKey(id)) {
            this.telporters.get(id).remove(blockPos);
            if(this.telporters.get(id).size() == 0) {
                this.telporters.remove(id);
                markDirty();
                return true;
            }
        }
        return false;
    }

    public boolean removeTeleporter(BlockPos blockPos) {
        return this.telporters.keySet().stream().anyMatch(integer -> removeTeleporter(integer, blockPos));
    }

    public BlockPos getOther(BlockPos blockPos) {
        for (List<BlockPos> blockPosList : this.telporters.values()) {
            if (blockPosList.contains(blockPos)) {
                List<BlockPos> newBlockList = new ArrayList<>(blockPosList);
                newBlockList.remove(blockPos);
                if (newBlockList.size() == 1) {
                    return newBlockList.get(0);
                }
            }
        }
        return null;
    }

    public static YastmWorldSaveData get(ServerWorld world) {
        return world.getSavedData().getOrCreate(YastmWorldSaveData::new, NAME);
    }
}
