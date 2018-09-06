package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.util.RegistryUtils;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import com.unrealdinnerbone.yastm.lib.util.TeleportationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileEntityTeleporter extends TileEntity implements ITickable {

    private int countTime;
    private TelerporterEffect telerporterEffect;
    private TeleporterParticleEffect teleporterParticleEffect;


    public TileEntityTeleporter() {
        this.telerporterEffect = RegistryUtils.getFirstValue(YastmRegistries.getFrequencyRegistry());
        this.teleporterParticleEffect = RegistryUtils.getFirstValue(YastmRegistries.getParticleEffectsRegistry());
        this.countTime = 0;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound myCompund = compound.copy();
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("effect", this.telerporterEffect.getRegistryName().toString());
        tagCompound.setString("peffect", this.teleporterParticleEffect.getRegistryName().toString());
        myCompund.setTag("yastm", tagCompound);
        return super.writeToNBT(myCompund);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("yastm")) {
            NBTTagCompound tagCompound = (NBTTagCompound) compound.getTag("yastm");
            if(tagCompound.hasKey("effect")) {
                String name = tagCompound.getString("effect");
                this.telerporterEffect = RegistryUtils.getRegistryObjectFormName(YastmRegistries.getFrequencyRegistry(), new ResourceLocation(name));
            } else {
                this.telerporterEffect = RegistryUtils.getFirstValue(YastmRegistries.getFrequencyRegistry());
            }
            if(tagCompound.hasKey("peffect")) {
                String name = tagCompound.getString("peffect");
                this.teleporterParticleEffect = RegistryUtils.getObjectOrElseFirst(YastmRegistries.getParticleEffectsRegistry(), new ResourceLocation(name));
            } else {
                this.teleporterParticleEffect = RegistryUtils.getFirstValue(YastmRegistries.getParticleEffectsRegistry());
            }

        }
    }


    public TelerporterEffect getTelerporterEffect() {
        return telerporterEffect;
    }

    public void setTelerporterEffect(TelerporterEffect telerporterEffect) {
        this.telerporterEffect = telerporterEffect;
        markDirty();
    }

    public void setTeleporterParticleEffect(TeleporterParticleEffect teleporterParticleEffect) {
        this.teleporterParticleEffect = teleporterParticleEffect;
        markDirty();
    }

    public TeleporterParticleEffect getTeleporterParticleEffect() {
        return teleporterParticleEffect;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            List<EntityPlayer> entities = scanForEntiesOnBlock();
            if (entities.size() > 0) {
                YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(world);
                DimBlockPos pos = new DimBlockPos(this.pos, world.provider.getDimension());
                if (worldSaveData.getTelerporterData().hasTwin(pos)) {
                    telerporterEffect.spawnPreTeleportEffect(teleporterParticleEffect.getEffect(), world, pos, countTime++);
                    if (countTime >= telerporterEffect.getTelerportTime()) {
                        DimBlockPos twinBlockPos = worldSaveData.getTelerporterData().getTwin(pos);
                        if (twinBlockPos != null) {
                            countTime = 0;
                            telerporterEffect.spawnTeleportEffect(teleporterParticleEffect.getEffect(), world, pos, entities.get(0));
                            TeleportationHelper.teleportPlayer(entities.get(0), twinBlockPos.getDimID(), twinBlockPos.getBlockPos().getX() + 0.5, twinBlockPos.getBlockPos().getY() + 0.125, twinBlockPos.getBlockPos().getZ() + 0.5);
                            telerporterEffect.spawnTelportArriveEffect(teleporterParticleEffect.getEffect(), world, twinBlockPos, entities.get(0));
                            world.playSound(null, pos.getBlockPos(), SoundEvents.ENTITY_SHULKER_TELEPORT, SoundCategory.PLAYERS, 1, 1);
                            entities.get(0).setSneaking(false);
                        } else {
                            Yastm.getInstance().getLogHelper().error("This should not happen");
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
}
