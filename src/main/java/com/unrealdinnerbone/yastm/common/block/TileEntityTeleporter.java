package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.TeleportationHelper;
import com.unrealdinnerbone.yastm.packet.PacketSpawnParticle;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TileEntityTeleporter extends TileEntity implements ITickable {

    private Color effectColor;
    private int countTime;


    public TileEntityTeleporter() {
        this.effectColor = new Color(69, 46, 255);
   }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound myCompund = compound.copy();
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setInteger("color", effectColor.getRGB());
        myCompund.setTag("yastm", tagCompound);
        return super.writeToNBT(myCompund);
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
            List<EntityPlayer> entities = scanForEntiesOnBlock();
            if (entities.size() > 0) {
                YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(world);
                DimBlockPos pos = new DimBlockPos(this.pos, world.provider.getDimension());
                if (worldSaveData.getTelerporterData().hasTwin(pos)) {
                    //Todo add config to allow the thing to be blah
                    countTime++;
                    if (countTime >= 20) {
                        DimBlockPos twinBlockPos = worldSaveData.getTelerporterData().getTwin(pos);
                        if (twinBlockPos != null) {
                            countTime = 0;
                            spawnEffect(pos.getBlockPos());
                            TeleportationHelper.teleportPlayer(entities.get(0), twinBlockPos.getDimID(), twinBlockPos.getBlockPos().getX() + 0.5, twinBlockPos.getBlockPos().getY() + 0.125, twinBlockPos.getBlockPos().getZ() + 0.5);
                            spawnEffect(twinBlockPos.getBlockPos());
                            world.playSound(null, pos.getBlockPos(), SoundEvents.ENTITY_SHULKER_TELEPORT, SoundCategory.PLAYERS, 1, 1);
//                            entities.get(0).setSneaking(false);
                        } else {
                            Yastm.getLogger().error("This should not happen");
                        }
                    }

                }
            } else {
                this.countTime = 0;
            }
        }
    }

    public List<EntityPlayer> scanForEntiesOnBlock() {
        List<EntityPlayer> entityPlayers = getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos(), getPos().add(1, 2, 1)));
        List<EntityPlayer> goodEnties = new ArrayList<>();
        entityPlayers.stream().filter(entity ->  !(entity == null || entity.isDead || entity.isRiding() || !entity.isSneaking())).forEach(goodEnties::add);
        return goodEnties;
    }


    @Nullable
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.writeToNBT(new NBTTagCompound());
        return writeToNBT(compound);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onDataPacket(net.minecraft.network.NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.getNbtCompound());
    }

    public int getCountTime() {
        return countTime;
    }

    private void spawnEffect(BlockPos blockPos) {
        PacketSpawnParticle packetSpawnParticle = new PacketSpawnParticle(effectColor, blockPos);
        Yastm.getNetworkWrapper().sendToAllAround(packetSpawnParticle, new NetworkRegistry.TargetPoint(world.provider.getDimension(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 32));
    }
}
