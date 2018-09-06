package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.common.block.YastmBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID)
public class EventRegisterItem {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> itemRegisterEvent) {
        Item itemBlock = new ItemBlock(YastmBlocks.TELEPORTER).setRegistryName(Yastm.MOD_ID, "teleporter");
        itemBlock.setUnlocalizedName("teleporter");
        itemRegisterEvent.getRegistry().register(new ItemBlock(YastmBlocks.TELEPORTER).setRegistryName(Yastm.MOD_ID, "teleporter"));
    }

}
