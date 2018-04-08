package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.client.gui.GUISelectFrequency;
import com.unrealdinnerbone.yastm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yaum.Yaum;
import com.unrealdinnerbone.yaum.api.network.ISimplePacket;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PacketOpenSetFrequencyGUI implements ISimplePacket<PacketOpenSetFrequencyGUI> {

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
        CharSequence name = buf.readCharSequence(l, StandardCharsets.UTF_8);
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
        buf.writeCharSequence(effect.getRegistryName().toString(), StandardCharsets.UTF_8);
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

    @SideOnly(Side.CLIENT)
    private void handle(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Yaum.proxy.displayGUIScreen(new GUISelectFrequency(message.getBlockPos(), message.getID(), message.getEffect()));
    }

    @Override
    public IMessage onMessage(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> handle(message, ctx));
        return null;
    }
}
