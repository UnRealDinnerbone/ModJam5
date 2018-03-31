package com.unrealdinnerbone.yatm.client.tesr;

import com.unrealdinnerbone.yatm.common.block.TileEntityTeleporter;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;

public class TESRTeleporter extends FastTESR<TileEntityTeleporter> {

    @Override
    public void renderTileEntityFast(TileEntityTeleporter te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
//            te.getFrequencyEffect().spawnPreTeleportEffect(te.getWorld(), new BlockPos(x, y, x), );
    }
}
