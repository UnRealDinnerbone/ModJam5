package com.unrealdinnerbone.yatm.packet.spawn;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class PacketSpawnParticle implements IMessage {

    private EnumParticleTypes particleType;
    private float xCoord;
    private float yCoord;
    private float zCoord;
    private float xOffset;
    private float yOffset;
    private float zOffset;
    private float particleSpeed;
    private int particleCount;
    private boolean longDistance;
    //Todo This Part
    private int[] particleArguments;

    public PacketSpawnParticle() {
    }

    public PacketSpawnParticle(EnumParticleTypes particleIn, boolean longDistanceIn, float xIn, float yIn, float zIn, float xOffsetIn, float yOffsetIn, float zOffsetIn, float speedIn, int countIn, int... argumentsIn) {
        this.particleType = particleIn;
        this.longDistance = longDistanceIn;
        this.xCoord = xIn;
        this.yCoord = yIn;
        this.zCoord = zIn;
        this.xOffset = xOffsetIn;
        this.yOffset = yOffsetIn;
        this.zOffset = zOffsetIn;
        this.particleSpeed = speedIn;
        this.particleCount = countIn;
        this.particleArguments = null;
    }

    @SideOnly(Side.CLIENT)
    public EnumParticleTypes getParticleType() {
        return this.particleType;
    }

    @SideOnly(Side.CLIENT)
    public boolean isLongDistance() {
        return this.longDistance;
    }

    @SideOnly(Side.CLIENT)
    public double getXCoordinate() {
        return (double) this.xCoord;
    }

    @SideOnly(Side.CLIENT)
    public double getYCoordinate() {
        return (double) this.yCoord;
    }

    @SideOnly(Side.CLIENT)
    public double getZCoordinate() {
        return (double) this.zCoord;
    }

    @SideOnly(Side.CLIENT)
    public float getXOffset() {
        return this.xOffset;
    }

    @SideOnly(Side.CLIENT)
    public float getYOffset() {
        return this.yOffset;
    }

    @SideOnly(Side.CLIENT)
    public float getZOffset() {
        return this.zOffset;
    }

    @SideOnly(Side.CLIENT)
    public float getParticleSpeed() {
        return this.particleSpeed;
    }

    @SideOnly(Side.CLIENT)
    public int getParticleCount() {
        return this.particleCount;
    }

    @SideOnly(Side.CLIENT)
    public int[] getParticleArgs() {
        return this.particleArguments;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.particleType = EnumParticleTypes.getParticleFromId(buf.readInt());

        if (this.particleType == null) {
            this.particleType = EnumParticleTypes.BARRIER;
        }

        this.longDistance = buf.readBoolean();
        this.xCoord = buf.readFloat();
        this.yCoord = buf.readFloat();
        this.zCoord = buf.readFloat();
        this.xOffset = buf.readFloat();
        this.yOffset = buf.readFloat();
        this.zOffset = buf.readFloat();
        this.particleSpeed = buf.readFloat();
        this.particleCount = buf.readInt();
        int i = this.particleType.getArgumentCount();
        this.particleArguments = new int[i];

//        for (int j = 0; j < i; ++j)
//        {
//            this.particleArguments[j] = buf.readVarInt();
//        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.particleType.getParticleID());
        buf.writeBoolean(this.longDistance);
        buf.writeFloat(this.xCoord);
        buf.writeFloat(this.yCoord);
        buf.writeFloat(this.zCoord);
        buf.writeFloat(this.xOffset);
        buf.writeFloat(this.yOffset);
        buf.writeFloat(this.zOffset);
        buf.writeFloat(this.particleSpeed);
        buf.writeInt(this.particleCount);
        int i = this.particleType.getArgumentCount();

//        for (int j = 0; j < i; ++j)
//        {
//            buf.writeVarInt(this.particleArguments[j]);
//        }
    }
}
