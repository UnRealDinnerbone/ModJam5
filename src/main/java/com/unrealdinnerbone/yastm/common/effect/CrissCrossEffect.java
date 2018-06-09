package com.unrealdinnerbone.yastm.common.effect;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yaum.api.register.IYaumObject;
import com.unrealdinnerbone.yaum.api.register.annotation.Register;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import com.unrealdinnerbone.yaum.libs.helpers.ParticleHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

@Register(Reference.MOD_ID)
public class CrissCrossEffect extends TelerporterEffect implements IYaumObject<TelerporterEffect> {

    @Override
    public int getTelerportTime() {
        return 20;
    }

    @Override
    public void spawnTeleportEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player) {

    }

    @Override
    public void spawnTelportArriveEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, Entity player) {

    }

    @Override
    public void spawnPreTeleportEffect(EnumParticleTypes particleType, World world, DimBlockPos blockPos, double count) {
        ParticleHelper.spawnParticleRing(world, particleType,  blockPos.getX() + 0.5,  blockPos.getY() + (count / 9.0),   blockPos.getZ() + 0.5, 0, 0.1, 0, 1);

    }

    @Override
    public TelerporterEffect get() {
        return this;
    }

    @Override
    public String getName() {
        return "crisscross";
    }
}
