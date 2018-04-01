package com.unrealdinnerbone.yatm.packet.set;

import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.common.event.register.EventRegisterRegisters;
import io.netty.buffer.ByteBuf;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

public class PacketSetFrequency implements IMessage {

    private BlockPos blockPos;
    private int ID;
    private BlockPos startPos;
    private int startID;
    private TelerporterEffect effect;

    public PacketSetFrequency() {

    }

    public PacketSetFrequency(BlockPos pos, int id, BlockPos startPos, int startID, TelerporterEffect telerporterEffect) {
        this.ID = id;
        this.blockPos = pos;
        this.startID = startID;
        this.startPos = startPos;
        this.effect = telerporterEffect;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
        ID = buf.readInt();
        startPos = BlockPos.fromLong(buf.readLong());
        startID = buf.readInt();
        int l = buf.readInt();
        CharSequence name = buf.readCharSequence(l, Charset.forName("utf-8"));
        for(Map.Entry<ResourceLocation, TelerporterEffect> entry :EventRegisterRegisters.getFrequencyRegistry().getEntries()) {
            if(entry.getValue().getRegistryName().toString().equalsIgnoreCase(name.toString())) {
                effect = entry.getValue();
                break;
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(ID);
        buf.writeLong(startPos.toLong());
        buf.writeInt(startID);
        buf.writeInt(effect.getRegistryName().toString().length());
        buf.writeCharSequence(effect.getRegistryName().toString(), Charset.forName("utf-8"));
    }

    public int getID() {
        return ID;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockPos getStartPos() {
        return startPos;
    }

    public int getStartID() {
        return startID;
    }

    public TelerporterEffect getEffect() {
        return effect;
    }
}
