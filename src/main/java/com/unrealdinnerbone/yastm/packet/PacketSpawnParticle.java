package com.unrealdinnerbone.yastm.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.awt.*;

public class PacketSpawnParticle implements ISimplePacket<PacketSpawnParticle> {

    private BlockPos blockPos;
    private Color color;

    public PacketSpawnParticle() {

    }

    public PacketSpawnParticle(Color color, BlockPos blockPos) {
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

    @Override
    public IMessage onMessage(PacketSpawnParticle message, MessageContext ctx) {
        BlockPos blockPos = message.blockPos;
        Color color = message.color;
        World world = Minecraft.getMinecraft().world;
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

        public ParticleRedstone(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float p_i46349_8_, float p_i46349_9_, float p_i46349_10_) {
            super(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i46349_8_, p_i46349_9_, p_i46349_10_);
        }
    }
}
