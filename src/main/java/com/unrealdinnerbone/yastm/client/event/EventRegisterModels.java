package com.unrealdinnerbone.yastm.client.event;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.common.item.YastmItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID, value = Side.CLIENT)
public class EventRegisterModels
{
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(YastmItems.TELEPORTER, 0, new ModelResourceLocation(YastmItems.TELEPORTER.getRegistryName(), "normal"));
    }
}
