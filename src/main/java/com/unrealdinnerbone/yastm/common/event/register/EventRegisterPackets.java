package com.unrealdinnerbone.yastm.common.event;

import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.packet.spawn.PacketSpawnParticleHandler;
import com.unrealdinnerbone.yaum.api.event.PacketRegisterEvent;
import com.unrealdinnerbone.yaum.libs.Reference;
import com.unrealdinnerbone.yaum.network.PacketSpawnParticle;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventRegisterPackets
{
    @SubscribeEvent
    public static void registerPackets(PacketRegisterEvent event) {
//        event.registerMessage(PacketSpawnParticle.class, PacketSpawnParticle.class, Side.CLIENT);
        event.registerMessage(PacketOpenSetFrequencyGUI.class, PacketOpenSetFrequencyGUI.class, Side.CLIENT);
        event.registerMessage(PacketOpenSetFrequencyGUIHandler.class, PacketOpenSetFrequencyGUI.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(PacketSpawnParticleHandler.class, PacketSpawnParticle.class, 3, Side.CLIENT);
    }
}