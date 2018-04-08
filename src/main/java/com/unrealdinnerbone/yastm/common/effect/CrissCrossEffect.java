package com.unrealdinnerbone.yatm.common.effect;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import com.unrealdinnerbone.yatm.lib.util.ParticleHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class CrissCrossEffect extends TelerporterEffect {

    @Override
    public int getTelerportTime() {
        return 20;
    }

    @Override
    public void spawnTeleportEffect(World world, DimBlockPos blockPos, EntityPlayer player) {

    }

    @Override
    public void spawnTelportArriveEffect(World world, DimBlockPos blockPos, EntityPlayer player) {

    }

    @Override
    public void spawnPreTeleportEffect(World world, DimBlockPos blockPos, double count) {
        ParticleHelper.spawnParticleRing(world, EnumParticleTypes.FLAME,  blockPos.getX() + 0.5,  blockPos.getY() + (count / 9.0),   blockPos.getZ() + 0.5, 0, 0.1, 0, 1);
    }
}
