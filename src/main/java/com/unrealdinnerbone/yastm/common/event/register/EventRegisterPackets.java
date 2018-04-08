package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import com.unrealdinnerbone.yaum.api.event.PacketRegisterEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventRegisterPackets
{
    @SubscribeEvent
    public static void registerPackets(PacketRegisterEvent event) {
        event.registerMessage(PacketOpenSetFrequencyGUI.class, PacketOpenSetFrequencyGUI.class, Side.CLIENT);
        event.registerMessage(PacketSetFrequency.class, PacketSetFrequency.class, Side.SERVER);
    }
}