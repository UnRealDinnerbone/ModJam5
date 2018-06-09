package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.lib.Reference;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.util.EnumParticleTypes.*;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventEffectRegister {

    @SubscribeEvent
    public static void registerEffect(RegistryEvent.Register<TeleporterParticleEffect> event) {
        registerEffect(event, WATER_BUBBLE);
        registerEffect(event, WATER_SPLASH);
        registerEffect(event, WATER_WAKE);
        registerEffect(event, SMOKE_NORMAL);
        registerEffect(event, ENCHANTMENT_TABLE);
        registerEffect(event, CLOUD);
        registerEffect(event, LAVA);
        registerEffect(event, DRAGON_BREATH);
        registerEffect(event, END_ROD);
        registerEffect(event, REDSTONE);
    }

    public static void registerEffect(RegistryEvent.Register<TeleporterParticleEffect> event, EnumParticleTypes type) {
        event.getRegistry().register(new TeleporterParticleEffect() {
            @Override
            public EnumParticleTypes getEffect() {
                return type;
            }
        }.setRegistryName(new ResourceLocation(Reference.MOD_ID, type.name().toLowerCase())));
    }
}
