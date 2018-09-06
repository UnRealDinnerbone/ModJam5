package com.unrealdinnerbone.yaum.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface ISimplePacket<T extends IMessage> extends IMessage, IMessageHandler<T, IMessage> {

    @Override
    default IMessage onMessage(T message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
            switch (ctx.side) {
                case CLIENT:
                    handlePacket(message, ctx, Minecraft.getMinecraft().player);
                    break;
                case SERVER:
                    handlePacket(message, ctx, ctx.getServerHandler().player);
                    break;
            }
        });
        return null;
    }

    default void handlePacket(T message, MessageContext ctx, EntityPlayer entityPlayer) {
        handlePacket(message, entityPlayer);
    }

    void handlePacket(T message, EntityPlayer entityPlayer);

}
