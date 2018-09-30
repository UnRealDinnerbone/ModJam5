package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenSetFrequencyGUI implements ISimplePacket<PacketOpenSetFrequencyGUI> {

    private DimBlockPos blockPos;
    private int ID;


    public PacketOpenSetFrequencyGUI() {

    }

    public PacketOpenSetFrequencyGUI(DimBlockPos pos, int id) {
        this.ID = id;
        this.blockPos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());
        ID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.getBlockPos().toLong());
        buf.writeInt(blockPos.getDimID());
        buf.writeInt(ID);
    }

    public int getID() {
        return ID;
    }

    public DimBlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public IMessage onMessage(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> Yastm.getProxy().openFrequenyGUI(message));

        return null;
    }
}
