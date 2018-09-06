package com.unrealdinnerbone.yaum.lib.util;

import com.unrealdinnerbone.yaum.common.network.PacketHandler;
import com.unrealdinnerbone.yaum.common.network.packets.PacketSpawnParticle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticleHelper {

    public static EnumParticleTypes getParticleFormID(int particleID) {
        return getParticleFormID(particleID, EnumParticleTypes.FLAME);
    }

    public static EnumParticleTypes getRandomParticle() {
        return EnumUtil.randomEnum(EnumParticleTypes.class);
    }

    public static EnumParticleTypes getParticleFormID(int particleID, EnumParticleTypes fallbackEffect) {
        EnumParticleTypes enumParticleTypes = EnumParticleTypes.getParticleFromId(particleID);
        return enumParticleTypes == null ? fallbackEffect : enumParticleTypes;
    }

    public static void spawnParticle(int id, boolean shouldIgnoreRange, float x, float y, float z, float xSpeed, float ySpeed, float zSpeed, int dimID) {
        PacketHandler.INSTANCE.sendToAll(new PacketSpawnParticle(getParticleFormID(id), x, y, z, zSpeed, ySpeed, zSpeed));
    }

    public static void spawnParticle(EnumParticleTypes particleType, float x, float y, float z, float xSpeed, float ySpeed, float zSpeed, World world) {
        spawnParticle(particleType.getParticleID(), particleType.getShouldIgnoreRange(), x, y, z, xSpeed, ySpeed, zSpeed, world.provider.getDimension());
    }

    public static void spawnParticleRing(World world, EnumParticleTypes particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ, double step) {
        for (double degree = 0.0d; degree < 2 * Math.PI; degree += step) {
            spawnParticle(particle, (float) x + (float) Math.cos(degree), (float) y, (float)z + (float) Math.sin(degree), (float) velocityX, (float) velocityY, (float) velocityZ, world);
        }
    }

}
