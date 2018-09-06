package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.lib.util.RegistryUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class PacketOpenSetFrequencyGUI implements IMessage {

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
        buf.writeLong(blockPos.getBlockPos().toLong());
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

}
