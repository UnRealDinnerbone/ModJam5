package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.common.command.CommandYastmTree;
import com.unrealdinnerbone.yastm.lib.LogHelper;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUIHandler;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequencyHandler;
import com.unrealdinnerbone.yastm.proxy.ClientProxy;
import com.unrealdinnerbone.yastm.proxy.Proxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Yastm.MOD_ID, name = Yastm.MOD_NAME, version = Yastm.VERSION)
public class Yastm {

    public static final String MOD_ID = "yastm";
    public static final String MOD_NAME = "Yet Another Simple Teleporter Mod";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance(Yastm.MOD_ID)
    public static Yastm INSTANCE;

    @SidedProxy(clientSide = "com.unrealdinnerbone.yastm.proxy.ClientProxy", serverSide = "com.unrealdinnerbone.yastm.proxy.ServerProxy")
    public static Proxy proxy;

    private static LogHelper logHelper;
    private static SimpleNetworkWrapper  networkWrapper;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
        logHelper = new LogHelper(event.getModLog());
        networkWrapper.registerMessage(PacketOpenSetFrequencyGUIHandler.class, PacketOpenSetFrequencyGUI.class, 1, Side.CLIENT);
        networkWrapper.registerMessage(PacketSetFrequencyHandler.class, PacketSetFrequency.class, 2, Side.SERVER);
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandYastmTree());
    }

    public LogHelper getLogHelper() {
        return logHelper;
    }

    public static Yastm getInstance() {
        return INSTANCE;
    }

    public static SimpleNetworkWrapper getNetworkWrapper() {
        return networkWrapper;
    }

    public static Proxy getProxy() {
        return proxy;
    }
}
