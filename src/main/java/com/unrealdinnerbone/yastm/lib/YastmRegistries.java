package com.unrealdinnerbone.yastm.lib;

import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import net.minecraftforge.registries.IForgeRegistry;

public class YastmRegistries
{
    private static IForgeRegistry<TelerporterEffect> frequencyRegistry;
    private static IForgeRegistry<TeleporterParticleEffect> particleEffectsRegistry;

    public static void setFrequencyRegistry(IForgeRegistry<TelerporterEffect> frequencyRegistry) {
        YastmRegistries.frequencyRegistry = frequencyRegistry;
    }

    public static IForgeRegistry<TelerporterEffect> getFrequencyRegistry() {
         return frequencyRegistry;
    }

    public static void setParticleEffectsRegistry(IForgeRegistry<TeleporterParticleEffect> particleEffectsRegistry) {
        YastmRegistries.particleEffectsRegistry = particleEffectsRegistry;
    }

    public static IForgeRegistry<TeleporterParticleEffect> getParticleEffectsRegistry() {
        return particleEffectsRegistry;
    }
}
