package com.jarhax.jewelersconstruct.client.container.slots;

import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotTrinketforgeInput extends SlotItemHandler {
    
    private final TileEntityTrinketForge tile;
    
    public SlotTrinketforgeInput(TileEntityTrinketForge tile, int index, int xPosition, int yPosition) {
        
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return tile.getLastType().getPartTypes().length > slotNumber && tile.getLastType().getPartTypes()[slotNumber].getPartItem() == stack.getItem();
    }
    
    
    
    @Override
    public void onSlotChanged() {
        tile.getInventory().setStackInSlot(4, ItemStack.EMPTY);
        super.onSlotChanged();
        
    }
}
