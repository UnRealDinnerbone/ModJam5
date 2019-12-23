package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.network.PacketSetFrequency;
import com.unrealdinnerbone.yastm.world.YastmWorldSaveData;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class YastmPackets
{
    private static final String PROTOCOL_VERSION = "1";


    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Yastm.MOD_ID, "main"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    static {
        int id = 0;
        INSTANCE.registerMessage(id++, PacketSetFrequency.class, (packetSetFrequency, packetBuffer) -> {
            packetBuffer.writeBlockPos(packetSetFrequency.getBlockPos());
            packetBuffer.writeInt(packetSetFrequency.getNewId());
        }, packetBuffer -> new PacketSetFrequency(packetBuffer.readBlockPos(), packetBuffer.readInt()), (packetSetFrequency, contextSupplier) -> {
            contextSupplier.get().enqueueWork(() -> {
                YastmWorldSaveData worldSaveData = YastmWorldSaveData.get(contextSupplier.get().getSender().getServerWorld());
                worldSaveData.removeTeleporter(packetSetFrequency.getBlockPos());
                worldSaveData.addTeleporter(packetSetFrequency.getNewId(), packetSetFrequency.getBlockPos());
            });
            contextSupplier.get().setPacketHandled(true);
        });
    }
}
