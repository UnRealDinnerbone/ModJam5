package com.unrealdinnerbone.yatm.common.event.register;

import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.lib.YatmBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventItemReigster {

    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(YatmBlocks.BLOCK_TELEPORTER).setRegistryName(Reference.MOD_ID, "teleporter"));
    }
}
