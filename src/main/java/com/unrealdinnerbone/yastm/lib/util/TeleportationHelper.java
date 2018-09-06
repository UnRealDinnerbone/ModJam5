package com.unrealdinnerbone.yastm.lib.util;

import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleportationHelper
{
    public static void teleportPlayer(EntityPlayer entityPlayer, int dimension, double destX, double destY, double destZ) {
        float rotationYaw = entityPlayer.rotationYaw;
        float rotationPitch = entityPlayer.rotationPitch;
        if (entityPlayer.getEntityWorld().provider.getDimension() != dimension) {
            teleportPlayerToDimension(entityPlayer, dimension, destX, destY, destZ);
        }
        entityPlayer.rotationYaw = rotationYaw;
        entityPlayer.rotationPitch = rotationPitch;
        entityPlayer.setPositionAndUpdate(destX, destY, destZ);
    }

    public static void teleportPlayer(EntityPlayer entityPlayer, DimBlockPos dimBlockPos) {
        teleportPlayer(entityPlayer, dimBlockPos.getDimID(), dimBlockPos.getBlockPos().getX(), dimBlockPos.getBlockPos().getY(), dimBlockPos.getBlockPos().getZ());
    }

    public static void teleportPlayerToDimension(EntityPlayer entityPlayer, int dimension, double x, double y, double z) {
        int formDimID = entityPlayer.getEntityWorld().provider.getDimension();
        if(entityPlayer instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            MinecraftServer server = entityPlayerMP.getEntityWorld().getMinecraftServer();
            WorldServer worldServer = server.getWorld(dimension);
            entityPlayerMP.addExperienceLevel(0);
            worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new YaumTeleporter(worldServer, x, y, z));
            entityPlayerMP.setPositionAndUpdate(x, y, z);
            if (formDimID == 1) {
                entityPlayerMP.setPositionAndUpdate(x, y, z);
                worldServer.spawnEntity(entityPlayerMP);
                worldServer.updateEntityWithOptionalForce(entityPlayerMP, false);
            }
        }
    }

    public static class YaumTeleporter extends Teleporter {

        private final WorldServer worldServer;

        private double x;
        private double y;
        private double z;


        public YaumTeleporter(WorldServer world, double x, double y, double z) {
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
}
