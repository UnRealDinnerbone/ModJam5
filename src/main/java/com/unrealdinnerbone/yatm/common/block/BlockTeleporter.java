package com.unrealdinnerbone.yatm.common.block;

import com.unrealdinnerbone.yatm.packet.PacketHandler;
import com.unrealdinnerbone.yatm.packet.open.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yatm.util.TelporterHelper;
import com.unrealdinnerbone.yatm.world.YatmWorldSaveData;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockTeleporter extends Block implements ITileEntityProvider {
    private final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

    public BlockTeleporter() {
        super(Material.GROUND, MapColor.CYAN);
        setUnlocalizedName("teleporter");
        this.fullBlock = false;
        this.setLightOpacity(255);

    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTeleporter();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            TileEntityTeleporter tileEntityTeleporter = (TileEntityTeleporter) tileEntity;
            if (playerIn instanceof EntityPlayerMP) {
                PacketHandler.INSTANCE.sendTo(new PacketOpenSetFrequencyGUI(pos, tileEntityTeleporter.getID()), (EntityPlayerMP) playerIn);
            }
        }
        return true;
    }

    int count = 0;
    private boolean particlesOn = false;

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        YatmWorldSaveData.get(worldIn).removeBlockPos(pos);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            TileEntityTeleporter tileEntityTeleporter = (TileEntityTeleporter) tileEntity;
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) entityIn;
                List<BlockPos> blockPosList = YatmWorldSaveData.get(worldIn).getPostionsFormID(tileEntityTeleporter.getID());
                if(blockPosList.size() >= 2) {
                    count++;
                    particlesOn = true;
                    if(count >= 20) {
                        BlockPos blockPos = YatmWorldSaveData.get(worldIn).getOtherPosFormIdAndPos(tileEntityTeleporter.getID(), pos);
                        TelporterHelper.performTeleport(entityPlayer, 0, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
                        count = 0;
                        particlesOn = false;
                    }
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if(particlesOn) {
            worldIn.spawnParticle(EnumParticleTypes.CLOUD, pos.getX(), pos.getY() + 1.5, pos.getZ(), 0, 1, 0, 10, 1000);
        }
    }
}
