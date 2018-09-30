package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.lib.util.RegistryUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;

public class PacketSpawnParticle implements ISimplePacket<PacketSpawnParticle> {

    private EnumParticleTypes particleType;
    private TelerporterEffect telerporterEffect;
    private DimBlockPos dimBlockPos;
    private int count;

    public PacketSpawnParticle() {

    }

    public PacketSpawnParticle(EnumParticleTypes particleType, TelerporterEffect telerporterEffect, DimBlockPos dimBlockPos, int count) {
        this.particleType = particleType;
        this.telerporterEffect = telerporterEffect;
        this.dimBlockPos = dimBlockPos;
        this.count = count;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.count = buf.readInt();
        this.dimBlockPos = new DimBlockPos(BlockPos.fromLong(buf.readLong()), buf.readInt());
        this.particleType = EnumParticleTypes.getParticleFromId(buf.readInt());
        int leg = buf.readInt();
        CharSequence charSequence = buf.readCharSequence(leg, StandardCharsets.UTF_8);
        this.telerporterEffect = RegistryUtils.getRegistryObjectFormName(YastmRegistries.getEffectRegistry(), new ResourceLocation(charSequence.toString()));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(count);
        buf.writeLong(dimBlockPos.getBlockPos().toLong());
        buf.writeInt(dimBlockPos.getDimID());
        buf.writeInt(particleType.getParticleID());
        CharSequence charSequence = new StringBuilder(telerporterEffect.getRegistryName().toString());
        buf.writeInt(charSequence.length());
        buf.writeCharSequence(charSequence, StandardCharsets.UTF_8);
    }

    @Override
    public void handlePacket(PacketSpawnParticle message, MessageContext ctx) {
        telerporterEffect.spawnEffect(particleType, Minecraft.getMinecraft().world, dimBlockPos, Minecraft.getMinecraft().player, count);
    }
}
