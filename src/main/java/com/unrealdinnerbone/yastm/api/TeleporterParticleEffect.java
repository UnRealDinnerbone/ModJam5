package com.unrealdinnerbone.yastm.api;

import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class TeleporterParticleEffect extends IForgeRegistryEntry.Impl<TeleporterParticleEffect> {

    public abstract EnumParticleTypes getEffect();

}
