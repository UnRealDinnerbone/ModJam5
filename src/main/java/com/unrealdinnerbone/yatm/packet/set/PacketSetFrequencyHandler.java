package com.unrealdinnerbone.yatm.packet.set;

import com.unrealdinnerbone.yatm.Yatm;
import com.unrealdinnerbone.yatm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yatm.world.YatmWorldSaveData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetFrequencyHandler implements IMessageHandler<PacketSetFrequency, IMessage> {

    private void handle(PacketSetFrequency message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().player;
        World world = player.getEntityWorld();
        if (world.isBlockLoaded(message.getBlockPos())) {
            TileEntity tileEntity = world.getTileEntity(message.getBlockPos());
            if (tileEntity instanceof TileEntityTeleporter) {
                TileEntityTeleporter telporter = (TileEntityTeleporter) tileEntity;
                telporter.setID(message.getID());
                telporter.markDirty();
                YatmWorldSaveData yatmWorldSaveData = YatmWorldSaveData.get(world);
                yatmWorldSaveData.removeBlockPos(message.getBlockPos());
                yatmWorldSaveData.addTelporter(message.getID(), message.getBlockPos());
                yatmWorldSaveData.save(world);
            }
        }
    }

    @Override
    public IMessage onMessage(PacketSetFrequency message, MessageContext ctx) {
        FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
        return null;
    }
}