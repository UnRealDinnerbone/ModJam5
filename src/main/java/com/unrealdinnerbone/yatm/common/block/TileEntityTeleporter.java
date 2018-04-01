package com.unrealdinnerbone.yatm.common.block;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import com.unrealdinnerbone.yatm.lib.util.TelporterHelper;
import com.unrealdinnerbone.yatm.world.YatmWorldSaveData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.Set;

public class TileEntityTeleporter extends TileEntity  {

    private TelerporterEffect frequencyEffect;
    private int count;


    public TileEntityTeleporter() {
        this.frequencyEffect = EventRegisterRegisters.getFrequencyRegistry().getEntries().stream().findFirst().get().getValue();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("name", frequencyEffect.getRegistryName().toString());
        compound.setTag("yatm", tagCompound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("yatm")) {
            NBTTagCompound tagCompound = (NBTTagCompound) compound.getTag("yatm");
            if(tagCompound.hasKey("name")) {
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
        DimBlockPos pos = new DimBlockPos(this.pos, world.provider.getDimension());
        if (worldSaveData.hasTwin(pos)) {
            count++;
            frequencyEffect.spawnPreTeleportEffect(world, pos, count);
            if (count >= frequencyEffect.getTelerportTime()) {
                DimBlockPos blockPos = worldSaveData.getTwin(pos);
                if (blockPos != null) {
                    count = 0;
                    frequencyEffect.spawnTeleportEffect(world, pos, entityPlayer);
                    TelporterHelper.performTeleport(entityPlayer, blockPos.getDimID(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
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
        markDirty();
    }
}
