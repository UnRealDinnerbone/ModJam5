package com.unrealdinnerbone.yatm.lib.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticleHelper
{
    public static void spawnParticle(World world, EnumParticleTypes particleType, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
//        PacketSpawnParticle spawnParticle = new PacketSpawnParticle(particleType, particleType.getShouldIgnoreRange(), (float) x, (float) y, (float) z, (float) x, (float) y, (float) z, 10, 1000);
        // Todo check for dispkaying
        Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(particleType.getParticleID(), x, y, z, xSpeed, ySpeed, zSpeed);

//        PacketHandler.INSTANCE.sendToAllAround(spawnParticle, new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, 16));
    }


    public static void spawnParticleRing(World world, EnumParticleTypes particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ, double step) {
        for (double degree = 0.0d; degree < 2 * Math.PI; degree += step) {
            spawnParticle(world, particle, x + Math.cos(degree), y, z + Math.sin(degree), velocityX, velocityY, velocityZ);
        }
    }
}
