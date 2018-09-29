package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.lib.LogHelper;
import com.unrealdinnerbone.yastm.proxy.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
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

    private static final String PROXY_BASE = "com.unrealdinnerbone.yastm.proxy.";
    private static final String CLIENT_PROXY = PROXY_BASE + "ClientProxy";
    private static final String SERVER_PROXY = PROXY_BASE + "Proxy";

    @Mod.Instance(Yastm.MOD_ID)
    public static Yastm INSTANCE;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static Proxy proxy;

    private static LogHelper logHelper;
    private static SimpleNetworkWrapper networkWrapper;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
        logHelper = new LogHelper(event.getModLog());
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
//        if(event.getSide() == Side.CLIENT) {
//            for (Class<? extends Entity> clazz : Minecraft.getMinecraft().getRenderManager().entityRenderMap.keySet()) {
//                Render<? extends Entity> thng = Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(clazz);
//                if (thng instanceof RenderLiving) {
//                    RenderJebHack renderJebHack = new RenderJebHack((RenderLiving) thng, ((RenderLiving) thng).getMainModel());
//                    ((RenderLiving) thng).addLayer(renderJebHack);
//                }
//            }
//
//        }
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        proxy.onServerStart(event);
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
