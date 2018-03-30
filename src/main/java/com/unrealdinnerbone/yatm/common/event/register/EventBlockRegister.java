package com.unrealdinnerbone.yatm.common.event.register;

import com.unrealdinnerbone.yatm.common.block.BlockTeleporter;
import com.unrealdinnerbone.yatm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventBlockRegister {

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<Block> event) {
        GameRegistry.registerTileEntity(TileEntityTeleporter.class, "teTeleporter");
        event.getRegistry().register(new BlockTeleporter().setRegistryName(Reference.MOD_ID, "teleporter"));
    }
}
