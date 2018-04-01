package com.unrealdinnerbone.yatm.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class YatmTeleporter extends Teleporter {

    private final WorldServer worldServer;

    private double x;
    private double y;
    private double z;


    public YatmTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.worldServer = world;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public void placeInPortal(Entity pEntity, float rotationYaw) {
        this.worldServer.getBlockState(new BlockPos(this.x, this.y, this.z));
        pEntity.setPosition(this.x, this.y, this.z);
        pEntity.motionX = 0.0f;
        pEntity.motionY = 0.0f;
        pEntity.motionZ = 0.0f;
    }




}
