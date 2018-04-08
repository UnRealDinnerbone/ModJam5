package com.unrealdinnerbone.yatm.common.event.register;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.common.effect.CrissCrossEffect;
import com.unrealdinnerbone.yatm.common.effect.RiseEffect;
import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventEffectRegister {

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<TelerporterEffect> event) {
        event.getRegistry().register(new CrissCrossEffect().setRegistryName(Reference.MOD_ID, "test_01"));
        for(EnumParticleTypes types: EnumParticleTypes.values()) {
            if(types.getArgumentCount() == 0) {
                event.getRegistry().register(new RiseEffect(types).setRegistryName(Reference.MOD_ID, "rise_" + types.getParticleName()));
            }
        }
    }
}
