package com.unrealdinnerbone.yatm.packet.open;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yatm.lib.DimBlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;
import java.util.Map;

public class PacketOpenSetFrequencyGUI implements IMessage {

    private DimBlockPos blockPos;
    private int ID;
    private TelerporterEffect effect;


    public PacketOpenSetFrequencyGUI() {

    }

    public PacketOpenSetFrequencyGUI(DimBlockPos pos, int id, TelerporterEffect effect) {
        this.ID = id;
        this.blockPos = pos;
        this.effect = effect;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());

        ID = buf.readInt();
        int l = buf.readInt();
        CharSequence name = buf.readCharSequence(l, Charset.forName("utf-8"));
        for (Map.Entry<ResourceLocation, TelerporterEffect> entry : EventRegisterRegisters.getFrequencyRegistry().getEntries()) {
            if (entry.getValue().getRegistryName().toString().equalsIgnoreCase(name.toString())) {
                effect = entry.getValue();
                break;
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(blockPos.getDimID());
        buf.writeInt(ID);
        buf.writeInt(effect.getRegistryName().toString().length());
        buf.writeCharSequence(effect.getRegistryName().toString(), Charset.forName("utf-8"));
    }

    public int getID() {
        return ID;
    }

    public DimBlockPos getBlockPos() {
        return blockPos;
    }

    public TelerporterEffect getEffect() {
        return effect;
    }
}
