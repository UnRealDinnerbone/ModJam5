package com.unrealdinnerbone.yastm.packet;

import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.lib.util.RegistryUtils;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.lang3.ObjectUtils;

public class PacketSetFrequencyHandler implements IMessageHandler<PacketSetFrequency, IMessage> {


    @Override
    public IMessage onMessage(PacketSetFrequency message, MessageContext ctx) {
        World world = ctx.getServerHandler().player.getEntityWorld();
        if (world.isBlockLoaded(message.getBlockPos().getBlockPos())) {
            TileEntity tileEntity = world.getTileEntity(message.getBlockPos().getBlockPos());
            if (tileEntity instanceof TileEntityTeleporter) {
                YatmWorldSaveData yatmWorldSaveData = YatmWorldSaveData.get(world);
                yatmWorldSaveData.getTelerporterData().removeTeleporter(message.getBlockPos());
                yatmWorldSaveData.getTelerporterData().addTeleporter(message.getID(), message.getBlockPos());
                yatmWorldSaveData.save(world);
                TileEntityTeleporter telporter = (TileEntityTeleporter) tileEntity;
                telporter.setTelerporterEffect(ObjectUtils.defaultIfNull(RegistryUtils.getRegistryObjectFormName(YastmRegistries.getFrequencyRegistry(), new ResourceLocation(message.getEffectID())), telporter.getTelerporterEffect()));
                telporter.setTeleporterParticleEffect(ObjectUtils.defaultIfNull(RegistryUtils.getRegistryObjectFormName(YastmRegistries.getParticleEffectsRegistry(), new ResourceLocation(message.getParticleID())), telporter.getTeleporterParticleEffect()));
            }
        }
        return null;
    }
}
