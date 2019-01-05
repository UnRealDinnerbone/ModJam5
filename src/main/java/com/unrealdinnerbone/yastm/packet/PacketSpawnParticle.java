package com.unrealdinnerbone.yastm.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class PacketSpawnParticle implements ISimplePacket<PacketSpawnParticle> {

    private BlockPos blockPos;
    private Color color;

    public PacketSpawnParticle() {

    }

    public PacketSpawnParticle(BlockPos blockPos, Color color) {
        this.color = color;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.color = new Color(buf.readInt());
        this.blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(color.getRGB());
        buf.writeLong(blockPos.toLong());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(PacketSpawnParticle message, MessageContext ctx) {
        BlockPos blockPos = message.blockPos;
        World world = Minecraft.getMinecraft().world;
        Color color = message.color;
        for (int i = 0; i < 20; i += 3) {
            for (double degree = 0.0d; degree < 2 * Math.PI; degree += 0.15) {
                double xCord = blockPos.getX() + 0.5 + Math.cos(degree) / 1.75;
                double yCord = blockPos.getY() + i * 0.1;
                double zCore = blockPos.getZ() + 0.5 - Math.sin(degree) / 1.75;
                ParticleRedstone particleRedstone = new ParticleRedstone(world, xCord, yCord,zCore, 0.01f, 0.1f, 0.01f);
                particleRedstone.setRBGColorF(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F);
                Minecraft.getMinecraft().effectRenderer.addEffect(particleRedstone);
            }

        }
        return null;
    }


    public static class ParticleRedstone extends net.minecraft.client.particle.ParticleRedstone {

        public ParticleRedstone(World worldIn, double x, double y, double z, float xSpeed, float ySpeed, float zSpeed) {
            super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
