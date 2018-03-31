package com.unrealdinnerbone.yatm.util;

import com.unrealdinnerbone.yatm.packet.PacketHandler;
import com.unrealdinnerbone.yatm.packet.spawn.PacketSpawnParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ParticleHelper
{
    public static void spawnParticle(World world, EnumParticleTypes particleType, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
//        PacketSpawnParticle spawnParticle = new PacketSpawnParticle(particleType, particleType.getShouldIgnoreRange(), (float) x, (float) y, (float) z, (float) x, (float) y, (float) z, 10, 1000);
        world.spawnParticle(particleType, x, y, z, xSpeed, ySpeed, zSpeed, 0, 0);
        Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(particleType.getParticleID(), x, y, z, xSpeed, ySpeed, zSpeed);

//        PacketHandler.INSTANCE.sendToAllAround(spawnParticle, new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, 16));
    }


    public static void spawnParticleRing(World world, EnumParticleTypes particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ, double step) {
        for (double degree = 0.0d; degree < 2 * Math.PI; degree += step) {
            spawnParticle(world, particle, x + Math.cos(degree), y, z + Math.sin(degree), velocityX, velocityY, velocityZ);
        }
    }
}
