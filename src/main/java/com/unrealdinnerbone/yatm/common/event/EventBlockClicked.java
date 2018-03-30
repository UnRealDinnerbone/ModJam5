package com.unrealdinnerbone.yatm.common.event;

import com.unrealdinnerbone.yatm.lib.Reference;
import com.unrealdinnerbone.yatm.lib.YatmBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventBlockClicked {

    {
        event.getPlayer().sendMessage(ChatColor.AQUA + "You clicked a " + ChatColor.BOLD + event.getClickedBlock().getType().toString().toLowerCase().replace("_", ""));
    }
        if(event.get()==Action.RIGHT_CLICK_BLOCK)

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        if (block.getType YatmBlocks.BLOCK_TELEPORTER)
    }
}
