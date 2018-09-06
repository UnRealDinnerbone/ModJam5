package com.unrealdinnerbone.yastm.common.te;


import com.unrealdinnerbone.yastm.lib.util.ReflectionUtils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class YaumTEBlock<T extends TileEntity> extends Block implements ITileEntityProvider {


    public YaumTEBlock(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    public YaumTEBlock(Material materialIn) {
        super(materialIn);
    }

    public abstract Class<T> getTileEntityClass();


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return ReflectionUtils.hasEmptyConstructor(getTileEntityClass()) ? ReflectionUtils.createInstance(getTileEntityClass()) : null;
    }

    public <T> T getTileEntity(World world, BlockPos blockPos) {
        TileEntity tileEntity = world.getTileEntity(blockPos);
        return getTileEntityClass().isInstance(tileEntity) ? (T) tileEntity : null;
    }
}
