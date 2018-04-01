package com.unrealdinnerbone.yatm;

import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.packet.PacketHandler;
import com.unrealdinnerbone.yatm.world.YatmWorldSaveData;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION
)
public class Yatm {


    @Mod.Instance(Reference.MOD_ID)
    public static Yatm INSTANCE;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        new PacketHandler();
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    public void onServerLoad(FMLServerStartingEvent event) {
        event.getServer().getEntityWorld();
    }

    //Todo Cross Dim TPing
    //Todo More Effect

}
