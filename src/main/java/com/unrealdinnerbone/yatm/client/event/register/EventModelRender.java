package com.unrealdinnerbone.yatm.client.event.register;

import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.lib.YatmBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
@SideOnly(Side.CLIENT)
public class EventModelRender {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void renderModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(YatmBlocks.BLOCK_TELEPORTER), 0, new ModelResourceLocation(YatmBlocks.BLOCK_TELEPORTER.getRegistryName(), "inventory"));
    }
}