package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.Yastm;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Yastm.MOD_ID)
public class YastmBlocks
{
    @ObjectHolder("teleporter")
    public static final Block TELEPORTER = Blocks.AIR;
}
