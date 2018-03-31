package com.unrealdinnerbone.yatm.common.event.register;

import com.unrealdinnerbone.yatm.api.FrequencyEffect;
import com.unrealdinnerbone.yatm.common.effect.RingEffect;
import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventEffectRegister {

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<FrequencyEffect> event) {
        event.getRegistry().register(new RingEffect().setRegistryName(Reference.MOD_ID, "ring"));
    }
}
