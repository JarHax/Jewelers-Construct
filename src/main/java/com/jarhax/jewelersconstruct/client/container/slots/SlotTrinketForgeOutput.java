package com.jarhax.jewelersconstruct.client.container.slots;

import javax.annotation.Nonnull;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.item.ItemJewelry;
import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotTrinketForgeOutput extends SlotItemHandler {
    
    private final TileEntityTrinketForge tile;
    
    public SlotTrinketForgeOutput(TileEntityTrinketForge tile, int index, int xPosition, int yPosition) {
        
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public boolean isItemValid (@Nonnull ItemStack stack) {
        
        return false;
    }
    
    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        if(!getStack().isEmpty()){
            if(getStack().getItem() instanceof ItemJewelry){
                return JewelryHelper.getRetention(getStack())>= JewelryHelper.getModifierCount(getStack());
            }
        }
        return super.canTakeStack(playerIn);
    }
    
    @Override
    public ItemStack onTake (EntityPlayer thePlayer, ItemStack stack) {
        
        for (int i = 0; i < this.tile.getInventory().getSlots(); i++) {
            
            final ItemStack slot = this.tile.getInventory().getStackInSlot(i);
            if (!slot.isEmpty()) {
                slot.shrink(1);
            }
        }
        return super.onTake(thePlayer, stack);
    }
}
