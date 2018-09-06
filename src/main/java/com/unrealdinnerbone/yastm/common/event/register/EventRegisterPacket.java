package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import com.unrealdinnerbone.yaum.common.network.PacketRegisterEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID)
public class EventRegisterPacket
{
    @SubscribeEvent
    public static void registerPackets(PacketRegisterEvent event) {
        event.registerPacket(PacketOpenSetFrequencyGUI.class, Side.CLIENT);
        event.registerPacket(PacketSetFrequency.class, Side.SERVER);
    }
}
