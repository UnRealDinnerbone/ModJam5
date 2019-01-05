package com.unrealdinnerbone.yastm.client.event;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.common.item.YastmItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID, value = Side.CLIENT)
public class EventRegisterModels {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(YastmItems.TELEPORTER);
    }

    public static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
    }
}