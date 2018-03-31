package com.unrealdinnerbone.yatm.packet.spawn;

import com.unrealdinnerbone.yatm.client.gui.GUISelectFrequency;
import com.unrealdinnerbone.yatm.packet.open.PacketOpenSetFrequencyGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnParticleHandler implements IMessageHandler<PacketSpawnParticle, IMessage> {


    @Override
    public IMessage onMessage(PacketSpawnParticle message, MessageContext ctx) {
        Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(message.getParticleType().getParticleID(), message.getXCoordinate(), message.getYCoordinate(), message.getZCoordinate(), message.getXOffset(), message.getYOffset(), message.getZOffset());
        return null;
    }
}