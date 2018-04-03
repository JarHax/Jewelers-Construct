package com.jarhax.jewelersconstruct.client.container.slots;

import com.jarhax.jewelersconstruct.item.ItemJewelry;
import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotTrinketForgeTrinket extends SlotItemHandler {
    
    private final TileEntityTrinketForge tile;
    
    public SlotTrinketForgeTrinket(TileEntityTrinketForge tile, int index, int xPosition, int yPosition) {
        
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        
        return stack.getItem() instanceof ItemJewelry;
    }
    
    @Override
    public void onSlotChanged() {
        
        this.tile.getInventory().setStackInSlot(4, ItemStack.EMPTY);
        super.onSlotChanged();
        
    }
}