package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.common.effect.RiseEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.util.EnumParticleTypes.*;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID)
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

    @SubscribeEvent
    public static void registerEffectAgain(RegistryEvent.Register<TelerporterEffect> effectRegistryEvent) {
        effectRegistryEvent.getRegistry().register(new RiseEffect().setRegistryName(Yastm.MOD_ID, "rise"));
    }

    public static void registerEffect(RegistryEvent.Register<TeleporterParticleEffect> event, EnumParticleTypes type) {
        event.getRegistry().register(new BasicTeleporterParticleEffect(type).setRegistryName(new ResourceLocation(Yastm.MOD_ID, type.name().toLowerCase())));
    }

    public static class BasicTeleporterParticleEffect extends TeleporterParticleEffect {

        private final EnumParticleTypes types;

        public BasicTeleporterParticleEffect(EnumParticleTypes enumParticleTypes) {
            this.types = enumParticleTypes;
        }

        public EnumParticleTypes getEffect() {
            return types;
        }

    }
}
