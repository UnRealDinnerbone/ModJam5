package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.common.effect.CrissCrossEffect;
import com.unrealdinnerbone.yastm.common.effect.RiseEffect;
import com.unrealdinnerbone.yastm.lib.Reference;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventEffectRegister {

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<TelerporterEffect> event) {
        for(EnumParticleTypes types: EnumParticleTypes.values()) {
            if(types.getArgumentCount() == 0) {
                event.getRegistry().register(new RiseEffect(types).setRegistryName(Reference.MOD_ID, "rise_" + types.getParticleName()));
            }
        }
    }
}
