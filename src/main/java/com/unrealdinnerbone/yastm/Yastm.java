package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yastm.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION
)
public class Yastm {


    @Mod.Instance(Reference.MOD_ID)
    public static Yastm INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT_SIDE, serverSide = Reference.SERVER_SIDE, modId = Reference.MOD_ID)
    public static CommonProxy proxy;



    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.onPreInt(event);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.onInt(event);
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.onPostInt(event);
    }

}
