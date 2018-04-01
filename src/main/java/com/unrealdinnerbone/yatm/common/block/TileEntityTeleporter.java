package com.unrealdinnerbone.yatm.common.block;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yatm.util.TelporterHelper;
import com.unrealdinnerbone.yatm.world.YatmWorldSaveData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.Set;

public class TileEntityTeleporter extends TileEntity  {

    private int ID;
    private TelerporterEffect frequencyEffect = EventRegisterRegisters.getFrequencyRegistry().getEntries().stream().findFirst().get().getValue();
    private int count;

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
        tagCompound.setString("name", frequencyEffect.getRegistryName().toString());
        compound.setTag("yatm", tagCompound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("yatm")) {
            NBTTagCompound tagCompound = (NBTTagCompound) compound.getTag("yatm");
            if(compound.hasKey("id")) {
                this.ID = tagCompound.getInteger("id");
            } else {
                this.ID = 0;
            }

            if(compound.hasKey("name")) {
                String name = tagCompound.getString("name");
                Set<Map.Entry<ResourceLocation, TelerporterEffect>> registry = EventRegisterRegisters.getFrequencyRegistry().getEntries();
                for(Map.Entry<ResourceLocation, TelerporterEffect> entry: registry) {
                    if(name.equalsIgnoreCase(entry.getValue().getRegistryName().toString())) {
                        this.frequencyEffect = entry.getValue();
                        return;
                    }
                }
                this.frequencyEffect = EventRegisterRegisters.getFrequencyRegistry().getEntries().stream().findFirst().get().getValue();

            } else {
                this.frequencyEffect = EventRegisterRegisters.getFrequencyRegistry().getEntries().stream().findFirst().get().getValue();
            }

        }
    }

    public void onEntityWalk(EntityPlayer entityPlayer) {
        YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(world);
        if (worldSaveData.hasTwin(ID, pos)) {
            count++;
            frequencyEffect.spawnPreTeleportEffect(world, pos, count);
            if (count >= frequencyEffect.getTelerportTime()) {
                BlockPos blockPos = worldSaveData.getTwin(ID, pos);
                if (blockPos != null) {
                    count = 0;
                    frequencyEffect.spawnTeleportEffect(world, pos, entityPlayer);
                    TelporterHelper.performTeleport(entityPlayer, world.provider.getDimension(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
                    frequencyEffect.spawnTelportArriveEffect(world, blockPos, entityPlayer);
                }
            }
        }
    }

    public TelerporterEffect getFrequencyEffect() {
        return frequencyEffect;
    }

    public int getCount() {
        return count;
    }

    public boolean doParticleSpawn() {
        return (count <= frequencyEffect.getTelerportTime()) && count != 0;
    }

    public void setFrequencyEffect(TelerporterEffect frequencyEffect) {
        this.frequencyEffect = frequencyEffect;
    }
}
