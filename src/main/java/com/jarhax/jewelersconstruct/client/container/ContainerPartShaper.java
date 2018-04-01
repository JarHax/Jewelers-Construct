package com.jarhax.jewelersconstruct.client.container;

import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class ContainerPartShaper extends Container {
    
    private final TileEntityPartShaper tile;
    
    public ContainerPartShaper(InventoryPlayer invPlayer, TileEntityPartShaper tile) {
        
        this.tile = tile;
        
        // TODO change these to slot specific ones, that accept certain items only
        this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), 0, 100+56, 17));
        this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), 1, 100+56, 53));
        this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), 2, 100+20, 35){
            @Override
            public boolean canTakeStack(EntityPlayer playerIn) {
                return false;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), 3, 100+116, 35));
    
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
}
