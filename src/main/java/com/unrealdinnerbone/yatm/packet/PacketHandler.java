package com.unrealdinnerbone.yatm.packet;

import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.packet.open.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yatm.packet.open.PacketOpenSetFrequencyGUIHandler;
import com.unrealdinnerbone.yatm.packet.set.PacketSetFrequency;
import com.unrealdinnerbone.yatm.packet.set.PacketSetFrequencyHandler;
import com.unrealdinnerbone.yatm.packet.spawn.PacketSpawnParticle;
import com.unrealdinnerbone.yatm.packet.spawn.PacketSpawnParticleHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE;

    public PacketHandler() {
        registerMessages();
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
        INSTANCE.registerMessage(PacketSetFrequencyHandler.class, PacketSetFrequency.class, 1, Side.SERVER);
        INSTANCE.registerMessage(PacketOpenSetFrequencyGUIHandler.class, PacketOpenSetFrequencyGUI.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(PacketSpawnParticleHandler.class, PacketSpawnParticle.class, 3, Side.CLIENT);
    }
}