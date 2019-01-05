package com.unrealdinnerbone.yastm.common.event.register;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.common.block.BlockTeleporter;
import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID)
public class EventRegisterBlock {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> blockRegisterEvent)
    {
        BlockTeleporter blockTeleporter = new BlockTeleporter();
        blockTeleporter.setRegistryName(Yastm.MOD_ID, "teleporter");
        blockRegisterEvent.getRegistry().register(blockTeleporter);

        GameRegistry.registerTileEntity(TileEntityTeleporter.class, new ResourceLocation(Yastm.MOD_ID, "te_teleporter"));
    }
}
