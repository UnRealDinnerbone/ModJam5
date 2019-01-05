package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.TeleportationHelper;
import com.unrealdinnerbone.yastm.packet.PacketSpawnParticle;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class TileEntityTeleporter extends TileEntity implements ITickable {

    private Color effectColor;
    private EntityPlayer lastPlayer;

    private int countTime;


    public TileEntityTeleporter() {
        this.effectColor = new Color(69, 46, 255);
        this.countTime = 0;
   }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound baseCompound = compound.copy();
        NBTTagCompound modCompound = new NBTTagCompound();
        modCompound .setInteger("color", effectColor.getRGB());
        baseCompound.setTag("yastm", modCompound);
        return super.writeToNBT(baseCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("yastm")) {
            NBTTagCompound tagCompound = (NBTTagCompound) compound.getTag("yastm");
            if(tagCompound.hasKey("color")) {
                int color = tagCompound.getInteger("color");
                this.effectColor = new Color(color);
            } else {
                this.effectColor = new Color(207, 248, 255);
            }

        }
    }

    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            EntityPlayer entityPlayer = getPlayerOnBlock();
            if (entityPlayer != null) {
                if(lastPlayer != null && entityPlayer.getUniqueID().equals(lastPlayer.getUniqueID())) {
                    return;
                }
                YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(world);
                DimBlockPos pos = new DimBlockPos(this.pos, world.provider.getDimension());
                if (worldSaveData.getTelerporterData().hasTwin(pos)) {
                    //Todo add config to allow the thing to be blah
                    countTime++;

                    if (this.countTime >= 20) {
                        DimBlockPos twinBlockPos = worldSaveData.getTelerporterData().getTwin(pos);
                        if (twinBlockPos != null) {
                            this.countTime = 0;
                            this.onPlayerTelerported(entityPlayer);
                            TeleportationHelper.teleportPlayer(entityPlayer, twinBlockPos.getDimID(), twinBlockPos.getBlockPos().getX() + 0.5, twinBlockPos.getBlockPos().getY() + 0.125, twinBlockPos.getBlockPos().getZ() + 0.5);
                            TileEntity tileEntity = world.getTileEntity(twinBlockPos.getBlockPos());
                            if(tileEntity instanceof TileEntityTeleporter) {
                                TileEntityTeleporter tileEntityTeleporter = (TileEntityTeleporter) tileEntity;
                                tileEntityTeleporter.onPlayerTelerported(entityPlayer);
                            }
                        } else {
                            Yastm.getLogger().error("This should not happen");
                        }
                    }

                }
            } else {
                this.countTime = 0;
                this.lastPlayer = null;
            }
        }
    }

    public void onPlayerTelerported(EntityPlayer entityPlayer) {
        world.playSound(null, pos, SoundEvents.ENTITY_SHULKER_TELEPORT, SoundCategory.PLAYERS, 1, 1);
        spawnEffect(pos);
        this.lastPlayer = entityPlayer;

    }

    @Nullable
    public EntityPlayer getPlayerOnBlock() {
        List<EntityPlayer> entityPlayers = getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos(), getPos().add(1, 2, 1)));
        return entityPlayers.stream().findFirst().filter(entity ->  !(entity.isDead || entity.isRiding() || !entity.isSneaking())).orElse(null);
    }


    @Nullable
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.writeToNBT(new NBTTagCompound());
        return writeToNBT(compound);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.getNbtCompound());
    }

    public int getCountTime() {
        return countTime;
    }

    private void spawnEffect(BlockPos blockPos) {
        PacketSpawnParticle packetSpawnParticle = new PacketSpawnParticle(blockPos, effectColor);
        Yastm.getNetworkWrapper().sendToAllAround(packetSpawnParticle, new NetworkRegistry.TargetPoint(world.provider.getDimension(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 32));
    }

    public boolean canTeleport(@Nonnull EntityPlayer entityPlayer) {
        return lastPlayer == null || !entityPlayer.getUniqueID().equals(lastPlayer.getUniqueID());
    }
}
