package com.unrealdinnerbone.yastm.lib;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID)
public class YastmRegistries
{
    private static IForgeRegistry<TelerporterEffect> frequencyRegistry;
    private static IForgeRegistry<TeleporterParticleEffect> particleEffectsRegistry;

    public static IForgeRegistry<TelerporterEffect> getFrequencyRegistry() {
         return frequencyRegistry;
    }

    public static IForgeRegistry<TeleporterParticleEffect> getParticleEffectsRegistry() {
        return particleEffectsRegistry;
    }

    @SubscribeEvent
    public static void onRegistryCreation(RegistryEvent.NewRegistry event) {
        frequencyRegistry = new RegistryBuilder<TelerporterEffect>().setType(TelerporterEffect.class).setMaxID(256).setName(new ResourceLocation(Yastm.MOD_ID, "effect")).create();
        particleEffectsRegistry = new RegistryBuilder<TeleporterParticleEffect>().setType(TeleporterParticleEffect.class).setMaxID(256).setName(new ResourceLocation(Yastm.MOD_ID, "particle")).create();
    }

}
