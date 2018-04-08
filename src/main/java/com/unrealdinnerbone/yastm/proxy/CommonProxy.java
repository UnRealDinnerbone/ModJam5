package com.unrealdinnerbone.yatm.proxy;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import com.unrealdinnerbone.yatm.packet.PacketHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy
{

    public void displayGUIScreen(DimBlockPos pos, int id, TelerporterEffect effect) {

    }

    public void onPreInt(FMLPreInitializationEvent event) {

    }

    public void onInt(FMLInitializationEvent event) {
        PacketHandler.registerMessages();
    }

    public void onPostInt(FMLPostInitializationEvent event) {

    }
}
