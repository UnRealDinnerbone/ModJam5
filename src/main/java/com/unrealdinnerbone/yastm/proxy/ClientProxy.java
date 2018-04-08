package com.unrealdinnerbone.yatm.proxy;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.client.gui.GUISelectFrequency;
import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

    @Override
    public void displayGUIScreen(DimBlockPos pos, int id, TelerporterEffect effect) {
        Minecraft.getMinecraft().displayGuiScreen(new GUISelectFrequency(pos, id, effect));
    }

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
