package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.Yastm;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Yastm.MOD_ID)
public class YastmTileEntites {

    @ObjectHolder("teleporter")
    public static final TileEntityType<?> TELEPORTER = TileEntityType.FURNACE;
}
