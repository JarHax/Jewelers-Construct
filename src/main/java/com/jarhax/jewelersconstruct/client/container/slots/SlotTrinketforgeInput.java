package com.jarhax.jewelersconstruct.client.container.slots;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.item.ItemJewelry;
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
        if(!tile.getInventory().getStackInSlot(5).isEmpty()) {
            return (JewelryHelper.INPUTS_TO_MODIFIERS.containsKey(stack)||!JewelryHelper.getModifiers(stack).isEmpty()) && !(stack.getItem() instanceof ItemJewelry) ;
        }
        return this.tile.getLastType().getPartTypes().length > this.slotNumber && this.tile.getLastType().getPartTypes()[this.slotNumber].getPartItem() == stack.getItem();
    }
    
    @Override
    public void onSlotChanged() {
        
        this.tile.getInventory().setStackInSlot(4, ItemStack.EMPTY);
        super.onSlotChanged();
        
    }
}
