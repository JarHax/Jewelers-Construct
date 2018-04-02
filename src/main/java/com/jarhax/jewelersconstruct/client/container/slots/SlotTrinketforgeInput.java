package com.jarhax.jewelersconstruct.client.container.slots;

import javax.annotation.Nonnull;

import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotTrinketforgeInput extends SlotItemHandler {
    
    private final TileEntityTrinketForge tile;
    
    public SlotTrinketforgeInput(TileEntityTrinketForge tile, int index, int xPosition, int yPosition) {
        
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public int getItemStackLimit (@Nonnull ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public boolean isItemValid (@Nonnull ItemStack stack) {
        
        return this.tile.getLastType().getPartTypes().length > this.slotNumber && this.tile.getLastType().getPartTypes()[this.slotNumber].getPartItem() == stack.getItem();
    }
    
    @Override
    public void onSlotChanged () {
        
        this.tile.getInventory().setStackInSlot(4, ItemStack.EMPTY);
        super.onSlotChanged();
        
    }
}
