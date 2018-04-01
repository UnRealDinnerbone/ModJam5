package com.unrealdinnerbone.yatm.common.event.register;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventRegisterRegisters {

    private static IForgeRegistry<TelerporterEffect> frequencyRegistry;

    @SubscribeEvent
    public static void onRegistryCreation(RegistryEvent.NewRegistry event) {
        frequencyRegistry = new RegistryBuilder<TelerporterEffect>().setType(TelerporterEffect.class).setMaxID(256).setName(new ResourceLocation(Reference.MOD_ID, "effect")).create();
    }

    public static IForgeRegistry<TelerporterEffect> getFrequencyRegistry() {
        return frequencyRegistry;
    }
}
