package com.unrealdinnerbone.yastm.api;

import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class TelerporterEffect extends IForgeRegistryEntry.Impl<TelerporterEffect> {

    public abstract int getTelerportTime();

    public abstract void spawnTeleportEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player);

    public abstract void spawnTelportArriveEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player);

    public abstract void spawnPreTeleportEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, double count);
}
