package com.unrealdinnerbone.yastm.event;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.YastmBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventRegisterItem {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> itemRegisterEvent) {
        itemRegisterEvent.getRegistry().register(new BlockItem(YastmBlocks.TELEPORTER, new Item.Properties()).setRegistryName(Yastm.MOD_ID, "teleporter"));
    }

}
