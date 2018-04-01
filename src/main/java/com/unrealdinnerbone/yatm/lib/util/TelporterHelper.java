package com.unrealdinnerbone.yatm.lib.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class TelporterHelper
{

    public static void performTeleport(EntityPlayer entityPlayer, int dimension, double destX, double destY, double destZ) {
        float rotationYaw = entityPlayer.rotationYaw;
        float rotationPitch = entityPlayer.rotationPitch;
        if (entityPlayer.getEntityWorld().provider.getDimension() != dimension) {
            teleportToDimension(entityPlayer, dimension, destX, destY, destZ);
        }
        entityPlayer.rotationYaw = rotationYaw;
        entityPlayer.rotationPitch = rotationPitch;
        entityPlayer.setPositionAndUpdate(destX, destY, destZ);
    }

    public static void teleportToDimension(EntityPlayer entityPlayer, int dimension, double x, double y, double z) {
        int formDimID = entityPlayer.getEntityWorld().provider.getDimension();
        if(entityPlayer instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            MinecraftServer server = entityPlayer.getEntityWorld().getMinecraftServer();
            WorldServer worldServer = server.getWorld(dimension);
            entityPlayer.addExperienceLevel(0);
            worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new YatmTeleporter(worldServer, x, y, z));
            entityPlayer.setPositionAndUpdate(x, y, z);
            if (formDimID == 1) {
                entityPlayer.setPositionAndUpdate(x, y, z);
                worldServer.spawnEntity(entityPlayer);
                worldServer.updateEntityWithOptionalForce(entityPlayer, false);
            }
        }
    }

}
