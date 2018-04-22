package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventRegisterRegisters {

    @SubscribeEvent
    public static void onRegistryCreation(RegistryEvent.NewRegistry event) {
        YastmRegistries.setFrequencyRegistry(new RegistryBuilder<TelerporterEffect>().setType(TelerporterEffect.class).setMaxID(256).setName(new ResourceLocation(Reference.MOD_ID, "effect")).create());
        YastmRegistries.setParticleEffectsRegistry(new RegistryBuilder<TeleporterParticleEffect>().setType(TeleporterParticleEffect.class).setMaxID(256).setName(new ResourceLocation(Reference.MOD_ID, "particle")).create());

    }

}
