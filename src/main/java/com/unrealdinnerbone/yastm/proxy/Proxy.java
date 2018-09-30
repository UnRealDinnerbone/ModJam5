package com.unrealdinnerbone.yastm.proxy;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.common.command.CommandYastmTree;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

public class Proxy
{
    public void openFrequenyGUI(PacketOpenSetFrequencyGUI message) {
        Yastm.getInstance().getLogHelper().error("Tried to open GUI on server? WHAAAT");
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        Yastm.getNetworkWrapper().registerMessage(PacketOpenSetFrequencyGUI.class, PacketOpenSetFrequencyGUI.class, 1, Side.CLIENT);
        Yastm.getNetworkWrapper().registerMessage(PacketSetFrequency.class, PacketSetFrequency.class, 2, Side.SERVER);
    }

    public void onInit(FMLInitializationEvent event) {

    }

    public void onPostInit(FMLPostInitializationEvent event) {

    }

    public void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandYastmTree());
    }



}
