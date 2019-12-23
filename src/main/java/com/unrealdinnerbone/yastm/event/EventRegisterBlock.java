package com.unrealdinnerbone.yastm.event;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.YastmBlocks;
import com.unrealdinnerbone.yastm.block.BlockTeleporter;
import com.unrealdinnerbone.yastm.tile.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Yastm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventRegisterBlock {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> blockRegisterEvent)
    {
        BlockTeleporter blockTeleporter = new BlockTeleporter();
        blockTeleporter.setRegistryName(Yastm.MOD_ID, "teleporter");
        blockRegisterEvent.getRegistry().register(blockTeleporter);
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityTeleporter::new, YastmBlocks.TELEPORTER).build(null).setRegistryName(Yastm.MOD_ID, "teleporter"));
    }
}
