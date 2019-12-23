package com.unrealdinnerbone.yastm.lib;


import net.minecraft.entity.player.PlayerEntity;

public class TeleportationHelper
{
    public static void teleportPlayer(PlayerEntity playerEntity, double destX, double destY, double destZ) {
//        float rotationYaw = playerEntity.rotationYaw;
//        float rotationPitch = playerEntity.rotationPitch;
//        playerEntity.rotationYaw = rotationYaw;
//        playerEntity.rotationPitch = rotationPitch;
        playerEntity.setPositionAndUpdate(destX, destY, destZ);
    }

}
