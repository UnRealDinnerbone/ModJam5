package com.unrealdinnerbone.yatm.common.effect;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import com.unrealdinnerbone.yatm.lib.util.ParticleHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RiseEffect extends TelerporterEffect {


    private final EnumParticleTypes TYPE;

    public RiseEffect(EnumParticleTypes enumParticleTypes) {
        this.TYPE = enumParticleTypes;
    }

    @Override
    public int getTelerportTime() {
        return 20;
    }

    @Override
    public void spawnTeleportEffect(World world, DimBlockPos blockPos, EntityPlayer player) {
        spawn(world, blockPos);
    }

    @Override
    public void spawnTelportArriveEffect(World world, DimBlockPos blockPos, EntityPlayer player) {
        spawn(world, blockPos);
    }

    @Override
    public void spawnPreTeleportEffect(World world, DimBlockPos blockPos, double count) {
        ParticleHelper.spawnParticleRing(world, TYPE,  blockPos.getX() + 0.5,  blockPos.getY() + (count / 9.0),   blockPos.getZ() + 0.5, 0, 0, 0, 0.1);
    }

    private void spawn(World world, BlockPos blockPos) {
        for (int i = 0; i < 20; i++) {
            ParticleHelper.spawnParticleRing(world, TYPE,  blockPos.getX() + 0.5,  blockPos.getY() + (i / 9.0),   blockPos.getZ() + 0.5, 0, 0, 0, 0.1);

        }
    }

}
