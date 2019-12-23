package com.unrealdinnerbone.yastm.block;

//import com.unrealdinnerbone.yastm.client.gui.GUISelectFrequency;
import com.unrealdinnerbone.yastm.tile.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;

public class BlockTeleporter extends Block implements ITileEntityProvider {

    protected static final VoxelShape SHAPE = VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

    public BlockTeleporter() {
        super(Block.Properties.create(Material.WOOD));
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    @Nullable
    public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos) {
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
    }


    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityTeleporter) {
            //Todo FIX ME
//            DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().displayGuiScreen(new GUISelectFrequency((TileEntityTeleporter) tileentity)));
            return true;
        } else {
            return false;
        }
    }


    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityTeleporter();
    }
}
