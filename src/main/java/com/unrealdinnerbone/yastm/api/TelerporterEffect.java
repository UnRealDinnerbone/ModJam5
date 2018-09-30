package com.unrealdinnerbone.yastm.api;

import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class TelerporterEffect extends IForgeRegistryEntry.Impl<TelerporterEffect> {

    public abstract void spawnEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player, int count);

}
