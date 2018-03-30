package com.unrealdinnerbone.yatm.common.event;

import com.unrealdinnerbone.yatm.lib.Reference;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventBlockClicked {


    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
//        IBlockState state = event.getWorld().getBlockState(event.getPos());
//        if (state.getBlock() == Blocks.STONE_SLAB) {
//            BlockStoneSlab stoneSlab = (BlockStoneSlab)
//        }
    }
}
