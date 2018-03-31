package com.unrealdinnerbone.yatm.api;

import com.unrealdinnerbone.yatm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yatm.util.TelporterHelper;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class FrequencyEffect extends IForgeRegistryEntry.Impl<FrequencyEffect>
{

    public abstract int getTelerportTime();

    public abstract void spawnTeleportEffect(World world, BlockPos blockPos, EntityPlayer player);

    public abstract void spawnTelportArriveEffect(World world, BlockPos blockPos, EntityPlayer player);

    public abstract void spawnPreTeleportEffect(World world, BlockPos blockPos, double count);
}
