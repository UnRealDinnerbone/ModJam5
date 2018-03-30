package com.unrealdinnerbone.yatm.event.register;

import com.unrealdinnerbone.yatm.block.BlockTeleporter;
import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventBlockRegister {

    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockTeleporter().setRegistryName(Reference.MOD_ID, "teleporter"));
    }
}
