package com.unrealdinnerbone.yastm.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy
{
    @Override
    public void onPreInt(FMLPreInitializationEvent event) {
        super.onPreInt(event);
    }

    @Override
    public void onInt(FMLInitializationEvent event) {
        super.onInt(event);
    }

    @Override
    public void onPostInt(FMLPostInitializationEvent event) {
        super.onPostInt(event);
    }
}
