package com.unrealdinnerbone.yastm.lib;

import com.unrealdinnerbone.yastm.Yastm;
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
    private static IForgeRegistry<TelerporterEffect> effectRegistry;

    public static IForgeRegistry<TelerporterEffect> getEffectRegistry() {
         return effectRegistry;
    }

    @SubscribeEvent
    public static void onRegistryCreation(RegistryEvent.NewRegistry event) {
        effectRegistry = new RegistryBuilder<TelerporterEffect>().setType(TelerporterEffect.class).setMaxID(256).setName(new ResourceLocation(Yastm.MOD_ID, "effect")).create();
    }

}
