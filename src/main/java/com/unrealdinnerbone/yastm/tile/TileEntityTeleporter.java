package com.unrealdinnerbone.yastm.tile;

import com.unrealdinnerbone.yastm.YastmTileEntites;
import com.unrealdinnerbone.yastm.lib.TeleportationHelper;
import com.unrealdinnerbone.yastm.world.YastmWorldSaveData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.awt.*;
import java.util.List;

public class TileEntityTeleporter extends TileEntity implements ITickableTileEntity {

    private Color effectColor;
    private PlayerEntity lastPlayer;
    private final NonNullList<ItemStack> stacks = NonNullList.create();

    private int countTime;


    public TileEntityTeleporter() {
        super(YastmTileEntites.TELEPORTER);
        this.effectColor = new Color(69, 46, 255);
        this.countTime = 0;
    }

    //    @Override
//    public CompoundNBT write(CompoundNBT compound) {
//        CompoundNBT compoundNBT = compound.copy();
//        CompoundNBT modNBT = new CompoundNBT();
//        modNBT.putInt("color", effectColor.getRGB());
//        compoundNBT.put("yastm", modNBT);
//        return super.write(compoundNBT);
//    }
//
//    @Override
//    public void read(CompoundNBT compound) {
//        super.read(compound);
//    }
//
//    @Override
//    public void readFromNBT(NBTTagCompound compound) {
//        super.readFromNBT(compound);
//        if(compound.hasKey("yastm")) {
//            NBTTagCompound tagCompound = (NBTTagCompound) compound.getTag("yastm");
//            if(tagCompound.hasKey("color")) {
//                int color = tagCompound.getInteger("color");
//                this.effectColor = new Color(color);
//            } else {
//                this.effectColor = new Color(207, 248, 255);
//            }
//
//        }
//    }
//
//    public Color getEffectColor() {
//        return effectColor;
//    }
//
//    public void setEffectColor(Color effectColor) {
//        this.effectColor = effectColor;
//    }
//
    @Override
    public void tick() {
        if(world instanceof ServerWorld && !world.isRemote) {
                PlayerEntity playerEntity = getPlayerOnBlock();
                if (playerEntity != null) {
                    if (lastPlayer == null || !playerEntity.getUniqueID().equals(lastPlayer.getUniqueID())) {
                        YastmWorldSaveData saveData = YastmWorldSaveData.get((ServerWorld) world);
                        this.countTime++;
                        if (this.countTime >= 20) {
                            BlockPos twinBlockPos = saveData.getOther(pos);
                            if (twinBlockPos != null) {
                                this.countTime = 0;
                                this.onPlayerTelerported(playerEntity);
                                TeleportationHelper.teleportPlayer(playerEntity, twinBlockPos.getX() + 0.5, twinBlockPos.getY() + 0.125, twinBlockPos.getZ() + 0.5);
                                TileEntity tileEntity = world.getTileEntity(twinBlockPos);
                                if (tileEntity instanceof TileEntityTeleporter) {
                                    TileEntityTeleporter tileEntityTeleporter = (TileEntityTeleporter) tileEntity;
                                    tileEntityTeleporter.onPlayerTelerported(playerEntity);
                                }
                            }else {
                                playerEntity.sendStatusMessage(new StringTextComponent("Error! No Other Location Found"), true);
                            }
                        }
                    }

                } else {
                    this.countTime = 0;
                    this.lastPlayer = null;
                }
        }
    }

    public void onPlayerTelerported(PlayerEntity entityPlayer) {
        world.playSound(null, pos, SoundEvents.ENTITY_SHULKER_TELEPORT, SoundCategory.PLAYERS, 1, 1);
        //Todo Fix Effects
        //        spawnEffect(pos);
        this.lastPlayer = entityPlayer;

    }
//
    public PlayerEntity getPlayerOnBlock() {
        List<PlayerEntity> entityPlayers = getWorld().getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(getPos(), getPos().add(1, 2, 1)));
        return entityPlayers.stream().findFirst().filter(Entity::isCrouching).orElse(null);
    }
//
//
//    @Nullable
//    @Override
//    public NBTTagCompound getUpdateTag() {
//        NBTTagCompound compound = super.writeToNBT(new NBTTagCompound());
//        return writeToNBT(compound);
//    }
//
//    public int getCountTime() {
//        return countTime;
//    }
//
//    private void spawnEffect(BlockPos blockPos) {
//        PacketSpawnParticle packetSpawnParticle = new PacketSpawnParticle(blockPos, effectColor);
//        Yastm.getNetworkWrapper().sendToAllAround(packetSpawnParticle, new NetworkRegistry.TargetPoint(world.provider.getDimension(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 32));
//    }
//
//    public boolean canTeleport(@Nonnull EntityPlayer entityPlayer) {
//        return lastPlayer == null || !entityPlayer.getUniqueID().equals(lastPlayer.getUniqueID());
//    }
    

}
