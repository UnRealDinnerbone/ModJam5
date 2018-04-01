package com.unrealdinnerbone.yatm.common.event;

import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.lib.YatmBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventBlockClicked {


    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        IBlockState state = event.getWorld().getBlockState(event.getPos());
        if (state.getBlock() == Blocks.STONE_SLAB) {
            System.out.println("G");
            if(event.getItemStack().getItem() == Items.ENDER_EYE) {
                event.getWorld().setBlockState(event.getPos(), YatmBlocks.BLOCK_TELEPORTER.getDefaultState());
            }
        }
    }
}
