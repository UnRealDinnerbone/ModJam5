package com.unrealdinnerbone.yastm.packet;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface ISimplePacket<T extends IMessage> extends IMessage, IMessageHandler<T, IMessage> {

    @Override
    default IMessage onMessage(T message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
            handlePacket(message, ctx);
        });
        return null;
    }

    void handlePacket(T message, MessageContext ctx);


}
