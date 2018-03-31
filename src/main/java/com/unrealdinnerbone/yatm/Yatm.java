package com.unrealdinnerbone.yatm;

import com.unrealdinnerbone.yatm.client.tesr.TESRTeleporter;
import com.unrealdinnerbone.yatm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.packet.PacketHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
        if(event.getSide() == Side.CLIENT) {
//            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeleporter.class, new TESRTeleporter());
        }
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

}
