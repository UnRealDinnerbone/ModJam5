package com.unrealdinnerbone.yatm.packet.open;

import com.unrealdinnerbone.yatm.client.gui.GUISelectFrequency;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenSetFrequencyGUIHandler implements IMessageHandler<PacketOpenSetFrequencyGUI, IMessage> {

    private void handle(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Minecraft.getMinecraft().displayGuiScreen(new GUISelectFrequency(message.getBlockPos(), message.getID(), message.getEffect()));
    }

    @Override
    public IMessage onMessage(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
        return null;
    }
}