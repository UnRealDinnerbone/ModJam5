package com.unrealdinnerbone.yastm.common.block;

import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import com.unrealdinnerbone.yaum.api.IYaumMod;
import com.unrealdinnerbone.yaum.api.register.annotation.Register;
import com.unrealdinnerbone.yaum.api.register.impl.IYaumBlock;
import com.unrealdinnerbone.yaum.common.network.PacketHandler;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

@Register(Reference.MOD_ID)
public class BlockTeleporter extends Block implements ITileEntityProvider, IYaumBlock {

    private final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

    public BlockTeleporter() {
        super(Material.GROUND, MapColor.CYAN);
        this.fullBlock = false;
        this.setLightOpacity(255);
        this.setCreativeTab(CreativeTabs.TRANSPORTATION);
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            TileEntityTeleporter tileEntityTeleporter = (TileEntityTeleporter) tileEntity;
            if (playerIn instanceof EntityPlayerMP) {
                YatmWorldSaveData saveData = YatmWorldSaveData.get(worldIn);
                DimBlockPos dimBlockPos = new DimBlockPos(pos, worldIn.provider.getDimension());
                PacketHandler.INSTANCE.sendTo(new PacketOpenSetFrequencyGUI(dimBlockPos, saveData.getIDFormPos(dimBlockPos), tileEntityTeleporter.getTelerporterEffect(), tileEntityTeleporter.getTeleporterParticleEffect()), (EntityPlayerMP) playerIn);
            }
        }
        return true;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        YatmWorldSaveData.get(worldIn).removeDimBlockPos(new DimBlockPos(pos, worldIn.provider.getDimension()));
    }


    @Override
    public Block get() {
        return this;
    }

    @Override
    public String getName() {
        return "teleporter";
    }

    @Override
    public void register(RegistryEvent.Register<Block> registryEvent, IYaumMod mod) {
        IYaumBlock.super.register(registryEvent, mod);
        GameRegistry.registerTileEntity(TileEntityTeleporter.class, "te" + getName());
    }
}
