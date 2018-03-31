package com.jarhax.jewelersconstruct.blocks;

import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPartShaper extends Block {
    
    public BlockPartShaper () {
        super(Material.IRON);
        
    }
    
    @Override
    public AxisAlignedBB getBoundingBox (IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0,0,0,1,1-0.0625,1);
    }
    
    @Override
    public boolean hasTileEntity (IBlockState state) {
        return true;
    }
    
    @Nullable
    @Override
    public TileEntity createTileEntity (World world, IBlockState state) {
        return new TileEntityPartShaper();
    }
}
