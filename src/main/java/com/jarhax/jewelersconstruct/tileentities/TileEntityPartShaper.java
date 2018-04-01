package com.jarhax.jewelersconstruct.tileentities;

import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPartShaper extends TileEntityBasicTickable {
    
    private final ItemStackHandler inventory;
    
    private int fuel = 0;
    private int fuelTotal = 0;
    
    private boolean processing = true;
    private int progress = 0;
    private final int progressMax = 100;
    
    public TileEntityPartShaper() {
        
        this.inventory = new ItemStackHandler(4);
    }
    
    @Override
    public void onEntityUpdate () {
        
        if (this.fuel <= 0) {
            this.fuelTotal = 0;
            final ItemStack fuelSlot = this.getInventory().getStackInSlot(1);
            if (!fuelSlot.isEmpty()) {
                if (TileEntityFurnace.getItemBurnTime(fuelSlot) > 0) {
                    this.fuel = TileEntityFurnace.getItemBurnTime(fuelSlot);
                    this.fuelTotal = this.fuel;
                    Item item = fuelSlot.getItem();
                    fuelSlot.shrink(1);
                    if(fuelSlot.isEmpty()){
                        
                        this.getInventory().setStackInSlot(1, item.getContainerItem(fuelSlot));
                    }
                }
            }
        }
        if (this.fuel > 0) {
            this.fuel--;
        }
        if (this.processing) {
            this.progress++;
            if (this.progress >= this.progressMax) {
                this.progress = 0;
                this.processing = false;
            }
        }
    }
    
    @Override
    public void writeNBT (NBTTagCompound dataTag) {
        
        dataTag.setTag("inventory", this.inventory.serializeNBT());
        dataTag.setInteger("fuel", this.fuel);
        dataTag.setInteger("fuelTotal", this.fuelTotal);
        dataTag.setInteger("progress", this.progress);
        dataTag.setBoolean("processing", this.processing);
        
    }
    
    @Override
    public void readNBT (NBTTagCompound dataTag) {
        
        this.inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
        this.fuelTotal = dataTag.getInteger("fuelTotal");
        this.progress = dataTag.getInteger("progress");
        this.processing = dataTag.getBoolean("processing");
    }
    
    public ItemStackHandler getInventory () {
        
        return this.inventory;
    }
    
    public int getFuel () {
        
        return this.fuel;
    }
    
    public void setFuel (int fuel) {
        
        this.fuel = fuel;
    }
    
    public int getFuelTotal () {
        
        return this.fuelTotal;
    }
    
    public void setFuelTotal (int fuelTotal) {
        
        this.fuelTotal = fuelTotal;
    }
    
    public boolean isProcessing () {
        
        return this.processing;
    }
    
    public int getProgress () {
        
        return this.progress;
    }
    
    public int getProgressMax () {
        
        return this.progressMax;
    }
}
