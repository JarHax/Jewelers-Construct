package com.jarhax.jewelersconstruct.tileentities;

import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPartShaper extends TileEntityBasicTickable {
    
    
    private ItemStackHandler inventory;
    
    private int fuel = 0;
    private int fuelTotal = 0;
    
    private boolean processing = true;
    private int progress = 0;
    private int progressMax = 100;
    
    public TileEntityPartShaper () {
        this.inventory = new ItemStackHandler(4);
    }
    
    @Override
    public void onEntityUpdate () {
        if (fuel <= 0) {
            fuelTotal = 0;
            ItemStack fuelSlot = getInventory().getStackInSlot(1);
            if (!fuelSlot.isEmpty()) {
                if (TileEntityFurnace.getItemBurnTime(fuelSlot) > 0) {
                    fuel = TileEntityFurnace.getItemBurnTime(fuelSlot);
                    fuelTotal = fuel;
                    fuelSlot.shrink(1);
                }
            }
        }
        if (fuel > 0) {
            fuel--;
        }
        processing = true;
        if(processing){
            progress++;
            if(progress>= progressMax){
                progress = 0;
                processing = false;
            }
        }
    }
    
    @Override
    public void writeNBT (NBTTagCompound dataTag) {
        dataTag.setTag("inventory", inventory.serializeNBT());
        dataTag.setInteger("fuel", fuel);
        dataTag.setInteger("fuelTotal", fuelTotal);
        dataTag.setInteger("progress", progress);
        dataTag.setBoolean("processing", processing);
        
    }
    
    @Override
    public void readNBT (NBTTagCompound dataTag) {
        inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
        fuelTotal = dataTag.getInteger("fuelTotal");
        progress = dataTag.getInteger("progress");
        processing = dataTag.getBoolean("processing");
    }
    
    
    public ItemStackHandler getInventory () {
        return inventory;
    }
    
    public int getFuel () {
        return fuel;
    }
    
    public void setFuel (int fuel) {
        this.fuel = fuel;
    }
    
    public int getFuelTotal () {
        return fuelTotal;
    }
    
    public void setFuelTotal (int fuelTotal) {
        this.fuelTotal = fuelTotal;
    }
    
    public boolean isProcessing () {
        return processing;
    }
    
    public int getProgress () {
        return progress;
    }
    
    public int getProgressMax () {
        return progressMax;
    }
}
