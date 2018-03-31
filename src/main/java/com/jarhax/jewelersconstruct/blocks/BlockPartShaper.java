package com.jarhax.jewelersconstruct.blocks;

import com.jarhax.jewelersconstruct.JewelersConstruct;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPartShaper extends Block {
    
    public BlockPartShaper () {
        super(Material.IRON);
        
    }
    
    @Override
    public boolean onBlockActivated (World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(JewelersConstruct.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox (IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0, 0, 0, 1, 1 - 0.0625, 1);
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
