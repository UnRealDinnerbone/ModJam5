package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.client.gui.GUISelectFrequency;
import com.unrealdinnerbone.yastm.common.event.register.EventRegisterRegisters;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yaum.Yaum;
import com.unrealdinnerbone.yaum.api.network.ISimplePacket;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import com.unrealdinnerbone.yaum.libs.utils.RegistryUtils;
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
    private TeleporterParticleEffect particleEffect;

    public PacketOpenSetFrequencyGUI() {

    }

    public PacketOpenSetFrequencyGUI(DimBlockPos pos, int id, TelerporterEffect effect, TeleporterParticleEffect particleEffect) {
        this.ID = id;
        this.blockPos = pos;
        this.effect = effect;
        this.particleEffect = particleEffect;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());

        ID = buf.readInt();
        int l = buf.readInt();
        CharSequence name = buf.readCharSequence(l, StandardCharsets.UTF_8);
        this.effect = RegistryUtils.getRegistryObjectFormName(YastmRegistries.getFrequencyRegistry(), new ResourceLocation(name.toString()));

        int l1 = buf.readInt();
        CharSequence name2 = buf.readCharSequence(l1, StandardCharsets.UTF_8);
        this.particleEffect = RegistryUtils.getRegistryObjectFormName(YastmRegistries.getParticleEffectsRegistry(), new ResourceLocation(name2.toString()));

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(blockPos.getDimID());
        buf.writeInt(ID);
        buf.writeInt(effect.getRegistryName().toString().length());
        buf.writeCharSequence(effect.getRegistryName().toString(), StandardCharsets.UTF_8);

        buf.writeInt(particleEffect.getRegistryName().toString().length());
        buf.writeCharSequence(particleEffect.getRegistryName().toString(), StandardCharsets.UTF_8);
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

    public TeleporterParticleEffect getParticleEffect() {
        return particleEffect;
    }

    @SideOnly(Side.CLIENT)
    private void handle(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Yaum.proxy.displayGUIScreen(new GUISelectFrequency(message.getBlockPos(), message.getID(), message.getEffect().getRegistryName().toString(), message.getParticleEffect().getRegistryName().toString()));
    }

    @Override
    public IMessage onMessage(PacketOpenSetFrequencyGUI message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> handle(message, ctx));
        return null;
    }
}
