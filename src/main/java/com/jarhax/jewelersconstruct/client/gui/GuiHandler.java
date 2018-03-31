package com.jarhax.jewelersconstruct.client.gui;

import javax.annotation.Nullable;

import com.jarhax.jewelersconstruct.client.container.ContainerPartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    
    @Nullable
    @Override
    public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
        
        switch (ID) {
            case 0:
                return new ContainerPartShaper(player.inventory, (TileEntityPartShaper) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
    
    @Nullable
    @Override
    public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
        
        switch (ID) {
            case 0:
                return new GuiPartShaper(player.inventory, (TileEntityPartShaper) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
