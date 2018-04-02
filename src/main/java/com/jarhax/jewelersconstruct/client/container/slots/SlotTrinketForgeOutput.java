package com.jarhax.jewelersconstruct.client.container.slots;

import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotTrinketForgeOutput extends SlotItemHandler {
    
    private final TileEntityTrinketForge tile;
    
    public SlotTrinketForgeOutput(TileEntityTrinketForge tile, int index, int xPosition, int yPosition) {
        
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }
    
    
    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        for(int i = 0; i < tile.getInventory().getSlots(); i++) {
            
            ItemStack slot = tile.getInventory().getStackInSlot(i);
            if(!slot.isEmpty()) {
                slot.shrink(1);
            }
        }
        return super.onTake(thePlayer, stack);
    }
}
