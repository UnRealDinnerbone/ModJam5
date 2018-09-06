package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.client.gui.GUISelectFrequency2;
import com.unrealdinnerbone.yastm.common.te.YaumTEBlock;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import mcjty.theoneprobe.network.PacketHandler;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTeleporter extends YaumTEBlock<TileEntityTeleporter> {

    private final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

    public BlockTeleporter() {
        super(Material.GROUND, MapColor.CYAN);
        this.fullBlock = false;
        this.setLightOpacity(255);
        this.setCreativeTab(CreativeTabs.TRANSPORTATION);
        this.setUnlocalizedName("teleporter");
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

    @Override
    public Class<TileEntityTeleporter> getTileEntityClass() {
        return TileEntityTeleporter.class;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntityTeleporter tileEntityTeleporter = getTileEntity(worldIn, pos);
            if (playerIn instanceof EntityPlayerMP) {
                YatmWorldSaveData saveData = YatmWorldSaveData.get(worldIn);
                DimBlockPos dimBlockPos = new DimBlockPos(pos, worldIn.provider.getDimension());
                Yastm.getNetworkWrapper().sendTo(new PacketOpenSetFrequencyGUI(dimBlockPos, saveData.getTelerporterData().getIDFormPos(dimBlockPos), tileEntityTeleporter.getTelerporterEffect(), tileEntityTeleporter.getTeleporterParticleEffect()), (EntityPlayerMP) playerIn);
            }
        }
        return true;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if(!worldIn.isRemote) {
            YatmWorldSaveData.get(worldIn).getTelerporterData().removeTeleporter(new DimBlockPos(pos, worldIn.provider.getDimension()));
        }
    }

//    @Override
//    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
//        TileEntityTeleporter tileEntityTeleporter = getTileEntity(world, data.getPos());
//        YatmWorldSaveData saveData = YatmWorldSaveData.get(world);
//        int id = saveData.getTelerporterData().getIDFormPos(new DimBlockPos(data.getPos(), world.provider.getDimension()));
//        probeInfo.progress(tileEntityTeleporter.getCountTime(), tileEntityTeleporter.getTelerporterEffect().getTelerportTime());
//        probeInfo.text("ID: " + id);
//        probeInfo.text("Telerporter Effect: " + tileEntityTeleporter.getTelerporterEffect().getRegistryName());
//        probeInfo.text("Particle Effect: " + tileEntityTeleporter.getTeleporterParticleEffect().getEffect().getParticleName());
//    }
}
