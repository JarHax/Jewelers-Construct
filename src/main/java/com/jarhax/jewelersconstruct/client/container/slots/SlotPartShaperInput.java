package com.jarhax.jewelersconstruct.client.container.slots;

import javax.annotation.Nonnull;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotPartShaperInput extends SlotItemHandler {
    
    private final TileEntityPartShaper tile;
    
    public SlotPartShaperInput(TileEntityPartShaper tile, int index, int xPosition, int yPosition) {
        
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public int getItemStackLimit (@Nonnull ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public boolean isItemValid (@Nonnull ItemStack stack) {
        
        return JewelryHelper.getMaterial(stack) != null;
    }
    
    @Override
    public boolean canTakeStack (EntityPlayer playerIn) {
        
        return !this.tile.isProcessing();
    }
}
