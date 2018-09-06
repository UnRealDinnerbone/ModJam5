package com.unrealdinnerbone.yastm.common.effect;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.util.ParticleHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class RiseEffect extends TelerporterEffect{

    @Override
    public int getTelerportTime() {
        return 20;
    }

    @Override
    public void spawnTeleportEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player) {
        spawn(particleType, world, blockPos.getBlockPos());

    }

    @Override
    public void spawnTelportArriveEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player) {
        spawn(particleType, world, blockPos.getBlockPos());

    }

    @Override
    public void spawnPreTeleportEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, double count) {
        //Todo
        spawn(particleType, world, blockPos.getBlockPos());
//        ParticleHelper.spawnParticleRing(world, particleType,  blockPos.getBlockPos().getX() + 0.5,  blockPos.getBlockPos().getY() + (count / 9.0),   blockPos.getBlockPos().getZ() + 0.5, 0, 0, 0, 0.1);

    }

    private void spawn(EnumParticleTypes particleType, World world, BlockPos blockPos) {
        for (int i = 0; i < 20; i++) {
            //Todo
            ParticleHelper.spawnParticleRing(world, particleType,  blockPos.getX() + 0.5,  blockPos.getY() + (i / 9.0),   blockPos.getZ() + 0.5, 0, 0, 0, 0.1);
        }
    }
}
