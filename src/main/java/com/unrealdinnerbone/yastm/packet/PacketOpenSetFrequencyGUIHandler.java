package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.Yastm;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenSetFrequencyGUIHandler implements IMessageHandler<PacketOpenSetFrequencyGUI, IMessage> {

    @Override
    public IMessage onMessage(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> Yastm.getProxy().openGUI(message));
        return null;
    }
}
