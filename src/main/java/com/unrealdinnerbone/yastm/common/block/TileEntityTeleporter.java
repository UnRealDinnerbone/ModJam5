package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import com.unrealdinnerbone.yaum.common.tile.YaumTileEntity;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import com.unrealdinnerbone.yaum.libs.helpers.TelporterHelper;
import com.unrealdinnerbone.yaum.libs.utils.RegistryUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class TileEntityTeleporter extends YaumTileEntity implements ITickable {

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
        List<Entity> entities = scanForEntiesOnBlock();
        if(entities.size() > 0) {
            YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(world);
            DimBlockPos pos = new DimBlockPos(this.pos, world.provider.getDimension());
            if (worldSaveData.hasTwin(pos)) {
                telerporterEffect.spawnPreTeleportEffect(teleporterParticleEffect.getEffect(), world, pos, countTime++);
                if (countTime >= telerporterEffect.getTelerportTime()) {
                    DimBlockPos twinBlockPos = worldSaveData.getTwin(pos);
                    if (twinBlockPos != null) {
                        countTime = 0;
                        telerporterEffect.spawnTeleportEffect(teleporterParticleEffect.getEffect(), world, pos, entities.get(0));
                        TelporterHelper.performTeleport(entities.get(0), twinBlockPos.getDimID(), twinBlockPos.getX() + 0.5, twinBlockPos.getY() + 0.125, twinBlockPos.getZ() + 0.5);
                        telerporterEffect.spawnTelportArriveEffect(teleporterParticleEffect.getEffect(), world, twinBlockPos, entities.get(0));
                        entities.get(0).setSneaking(false);
                    } else {
                        Yastm.INSTANCE.getLogHelper().error("This should not happen");
                    }
                }

            }
        }else {
            this.countTime = 0;
        }
    }

    public List<Entity> scanForEntiesOnBlock() {
        List<Entity> entities = getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos(), getPos().add(1, 2, 1)));
        List<Entity> goodEnties = new ArrayList<>();
        entities.stream().filter(entity ->  !(entity == null || entity.isDead || entity.isRiding() || !entity.isSneaking())).forEach(goodEnties::add);
        return goodEnties;
    }
}
