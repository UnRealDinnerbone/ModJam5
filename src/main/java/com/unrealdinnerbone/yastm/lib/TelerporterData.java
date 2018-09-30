package com.unrealdinnerbone.yastm.lib;

import com.unrealdinnerbone.yastm.Yastm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TelerporterData {

    private HashMap<Integer, List<DimBlockPos>> positions;

    public TelerporterData() {
        initOrClear();
    }

    public boolean checkID(int id) {
        return id != 0;
    }

    public boolean hasDimBlockPos(int id, DimBlockPos dimBlockPos) {
        return hasID(id) && positions.get(id).stream().anyMatch(checkBlockPos -> checkBlockPos.equals(dimBlockPos));
    }

    public boolean hasDimBlockPos(DimBlockPos dimBlockPos) {
        return hasDimBlockPos(getIDFromBlockPos(dimBlockPos), dimBlockPos);
    }

    public void initOrClear() {
        if (this.positions == null) {
            this.positions = new HashMap<>();
        } else {
            this.positions.clear();
        }
    }

    public boolean hasID(int id) {
        return this.positions.containsKey(id);
    }

    public void initIDIfEmpty(int id) {
        if(!hasID(id)) {
            this.positions.put(id, new ArrayList<>());
        }
    }

    public void addTeleporter(int id, @Nonnull DimBlockPos blockPos) {
        if (checkID(id)) {
            initIDIfEmpty(id);
            if (!hasDimBlockPos(blockPos)) {
                if(this.positions.get(id).size() < 2) {
                    this.positions.get(id).add(blockPos);
                }else {
                    Yastm.getLogger().error("BlockPos {0} not add there are already two blockpos with the id {1}", blockPos.toString(), id);
                }
            } else {
                Yastm.getLogger().error("BlockPos {0} is already ", blockPos.toString());
            }
        } else {
            Yastm.getLogger().error("Telerported can't be added with ID of 0 {0}", blockPos.toString());
        }
    }

    public void removeTeleporter(int id, DimBlockPos blockPos) {
        if(hasID(id)) {
            if(hasDimBlockPos(id, blockPos)) {
                List<DimBlockPos> blockPosList = this.positions.get(id);
                List<DimBlockPos> toRemove = new ArrayList<>();
                for (DimBlockPos dimBlockPos : blockPosList) {
                    if (dimBlockPos.equals(blockPos)) {
                        toRemove.add(dimBlockPos);
                    }
                }
                blockPosList.removeAll(toRemove);
                if(blockPosList.size() == 0) {
                    this.positions.remove(id);
                }
            }
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
        if(checkID(ID) && hasID(ID)) {
            List<DimBlockPos> posList = positions.get(ID);
            if(posList.size() == 2) {
                List<DimBlockPos> dummyList = new ArrayList<>(posList);
                List<DimBlockPos> toRemove = posList.stream().filter(blockPos::equals).collect(Collectors.toList());
                dummyList.removeAll(toRemove);
                return dummyList.size() == 1;
            }else {
                //Todo some that says WHAT? pos list should never be bigger then 2
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


    public List<DimBlockPos> getPostionsFormID(int id) {
        return checkID(id) && hasID(id) ? this.positions.get(id) : new ArrayList<>();
    }


    public void removeTeleporter(DimBlockPos blockPos) {
        removeTeleporter(getIDFromBlockPos(blockPos), blockPos);
    }

    public int getIDFromBlockPos(DimBlockPos dimBlockPos) {
        return positions.keySet().stream().mapToInt(key -> key).filter(key -> positions.get(key).stream().anyMatch(dimBlockPos::equals)).findFirst().orElse(0);
    }

    public Set<Integer> getKeySet() {
        return this.positions.keySet();
    }

    public List<DimBlockPos> getBlockPosFromId(int id) {
        return checkID(id) && hasID(id) ? this.positions.get(id) : new ArrayList<>();
    }

}
