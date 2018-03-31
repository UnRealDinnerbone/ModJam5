package com.unrealdinnerbone.yatm.common.effect;

import com.unrealdinnerbone.yatm.api.FrequencyEffect;
import com.unrealdinnerbone.yatm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yatm.util.ParticleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RingEffect extends FrequencyEffect {


    @Override
    public int getTelerportTime() {
        return 20;
    }

    @Override
    public void spawnTeleportEffect(World world, BlockPos blockPos, EntityPlayer player) {
        spawn(world, blockPos, EnumParticleTypes.SMOKE_LARGE);
    }

    @Override
    public void spawnTelportArriveEffect(World world, BlockPos blockPos, EntityPlayer player) {
        spawn(world, blockPos, EnumParticleTypes.SMOKE_LARGE);
    }

    @Override
    public void spawnPreTeleportEffect(World world, BlockPos blockPos, int count) {
        spawn(world, blockPos.add(0, count / 9.0, 0), EnumParticleTypes.FLAME);
    }

    private void spawn(World world, BlockPos blockPos, EnumParticleTypes types) {
        ParticleHelper.spawnParticleRing(world, types, blockPos.getX() + 0.5, blockPos.getY() + 0.5,  blockPos.getZ() + 0.5, 0, 0, 0, 0.1);

    }

}
