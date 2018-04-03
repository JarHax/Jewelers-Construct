package com.jarhax.jewelersconstruct.client.container;

import java.util.ArrayList;
import java.util.List;

import com.jarhax.jewelersconstruct.client.container.slots.*;
import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrinketForge extends Container {
    
    private final TileEntityTrinketForge tile;
    
    public ContainerTrinketForge(InventoryPlayer invPlayer, TileEntityTrinketForge tile) {
        
        this.tile = tile;
        this.addSlotToContainer(new SlotTrinketforgeInput(tile, 0, 100 + 20, 35));
        this.addSlotToContainer(new SlotTrinketforgeInput(tile, 1, 100 + 40, 35));
        this.addSlotToContainer(new SlotTrinketforgeInput(tile, 2, 100 + 60, 35));
        this.addSlotToContainer(new SlotTrinketforgeInput(tile, 3, 100 + 80, 35));
        this.addSlotToContainer(new SlotTrinketForgeOutput(tile, 4, 100 + 120, 35));
        this.addSlotToContainer(new SlotTrinketForgeTrinket(tile, 5, 100 + 50, 15));
    
    
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(invPlayer, x, 108 + 18 * x, 142));
        }
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 108 + 18 * x, 84 + y * 18));
            }
        }
    }
    
    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean canInteractWith (EntityPlayer player) {
        
        if (player.world.getTileEntity(this.tile.getPos()) != this.tile) {
            return false;
        }
        else {
            return player.getDistanceSq(this.tile.getPos().getX() + 0.5D, this.tile.getPos().getY() + 0.5D, this.tile.getPos().getZ() + 0.5D) <= 64.0D;
        }
    }
    
    @Override
    public ItemStack transferStackInSlot (EntityPlayer playerIn, int index) {
        
        // TODO change to work with isItemValid ==false
        ItemStack itemStack;
        final Slot clickSlot = this.inventorySlots.get(index);
        
        if (clickSlot != null && clickSlot.getHasStack()) {
            itemStack = clickSlot.getStack();
            
            if (itemStack.isEmpty()) {
                return ItemStack.EMPTY;
            }
            final List<Slot> selectedSlots = new ArrayList<>();
            
            if (clickSlot.inventory instanceof InventoryPlayer) {
                for (int x = 0; x < this.inventorySlots.size(); x++) {
                    final Slot advSlot = this.inventorySlots.get(x);
                    if (advSlot.isItemValid(itemStack)) {
                        selectedSlots.add(advSlot);
                    }
                }
            }
            else {
                for (int x = 0; x < this.inventorySlots.size(); x++) {
                    final Slot advSlot = this.inventorySlots.get(x);
                    
                    if (advSlot.inventory instanceof InventoryPlayer) {
                        if (advSlot.isItemValid(itemStack)) {
                            selectedSlots.add(advSlot);
                        }
                    }
                }
            }
            
            if (!itemStack.isEmpty()) {
                for (final Slot slot : selectedSlots) {
                    if (slot.isItemValid(itemStack) && !itemStack.isEmpty()) {
                        if (slot.getHasStack()) {
                            final ItemStack stack = slot.getStack();
                            
                            if (!itemStack.isEmpty() && itemStack.isItemEqual(stack)) {
                                int maxSize = stack.getMaxStackSize();
                                
                                if (maxSize > slot.getSlotStackLimit()) {
                                    maxSize = slot.getSlotStackLimit();
                                }
                                
                                int placeAble = maxSize - stack.getCount();
                                
                                if (itemStack.getCount() < placeAble) {
                                    placeAble = itemStack.getCount();
                                }
                                
                                stack.grow(placeAble);
                                itemStack.shrink(placeAble);
                                
                                if (itemStack.getCount() <= 0) {
                                    clickSlot.putStack(ItemStack.EMPTY);
                                    slot.onSlotChanged();
                                    this.updateSlot(clickSlot);
                                    this.updateSlot(slot);
                                    return ItemStack.EMPTY;
                                }
                                
                                this.updateSlot(slot);
                            }
                        }
                        else {
                            int maxSize = itemStack.getMaxStackSize();
                            
                            if (maxSize > slot.getSlotStackLimit()) {
                                maxSize = slot.getSlotStackLimit();
                            }
                            
                            final ItemStack tmp = itemStack.copy();
                            
                            if (tmp.getCount() > maxSize) {
                                tmp.setCount(maxSize);
                            }
                            itemStack.shrink(tmp.getCount());
                            slot.putStack(tmp);
                            
                            if (itemStack.getCount() <= 0) {
                                clickSlot.putStack(ItemStack.EMPTY);
                                slot.onSlotChanged();
                                this.updateSlot(clickSlot);
                                this.updateSlot(slot);
                                return ItemStack.EMPTY;
                            }
                            
                            this.updateSlot(slot);
                        }
                    }
                }
            }
            
            clickSlot.putStack(!itemStack.isEmpty() ? itemStack.copy() : ItemStack.EMPTY);
        }
        this.updateSlot(clickSlot);
        return ItemStack.EMPTY;
    }
    
    private void updateSlot (final Slot slot) {
        
        this.detectAndSendChanges();
    }
    
}
