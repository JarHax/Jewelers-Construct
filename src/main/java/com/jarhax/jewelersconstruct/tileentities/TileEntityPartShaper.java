package com.jarhax.jewelersconstruct.tileentities;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.part.PartType;
import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPartShaper extends TileEntityBasicTickable {
    
    private final ItemStackHandler inventory;
    
    private int fuel = 0;
    private int fuelTotal = 0;
    
    private boolean processing = false;
    private int progress = 0;
    private final int progressMax = 100;
    private PartType lastType = JewelryHelper.PART_TYPES.getValues().get(0);
    
    public TileEntityPartShaper() {
        
        this.inventory = new ItemStackHandler(3);
    }
    
    @Override
    public void onEntityUpdate() {
        if(this.fuel <= 0) {
            this.fuelTotal = 0;
            final ItemStack fuelSlot = this.getInventory().getStackInSlot(1);
            if(!fuelSlot.isEmpty()) {
                if(TileEntityFurnace.getItemBurnTime(fuelSlot) > 0) {
                    this.fuel = TileEntityFurnace.getItemBurnTime(fuelSlot);
                    this.fuelTotal = this.fuel;
                    Item item = fuelSlot.getItem();
                    fuelSlot.shrink(1);
                    if(fuelSlot.isEmpty()) {
                        
                        this.getInventory().setStackInSlot(1, item.getContainerItem(fuelSlot));
                    }
                }
            }
        }
        if(this.processing) {
            if(this.fuel > 0) {
                this.fuel--;
                
                this.progress++;
                if(this.progress >= this.progressMax) {
                    this.progress = 0;
                    this.processing = false;
                    Material material = JewelryHelper.getMaterial(inventory.getStackInSlot(0));
                    inventory.getStackInSlot(0).shrink(1);
                    ItemStack itemStack = new ItemStack(lastType.getPartItem());
                    
                    JewelryHelper.setMaterial(itemStack, material);
                    inventory.setStackInSlot(2, itemStack);
                }
            }
        }
    }
    
    @Override
    public void writeNBT(NBTTagCompound dataTag) {
        
        dataTag.setTag("inventory", this.inventory.serializeNBT());
        dataTag.setInteger("fuel", this.fuel);
        dataTag.setInteger("fuelTotal", this.fuelTotal);
        dataTag.setInteger("progress", this.progress);
        dataTag.setBoolean("processing", this.processing);
        if(getLastType() != null)
            dataTag.setString("lastPart", getLastType().getRegistryName().toString());
    }
    
    @Override
    public void readNBT(NBTTagCompound dataTag) {
        
        this.inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
        this.fuelTotal = dataTag.getInteger("fuelTotal");
        this.progress = dataTag.getInteger("progress");
        this.processing = dataTag.getBoolean("processing");
        this.lastType = JewelryHelper.getPartTypeByName(dataTag.getString("lastPart"));
    }
    
    public ItemStackHandler getInventory() {
        
        return this.inventory;
    }
    
    public int getFuel() {
        
        return this.fuel;
    }
    
    public void setFuel(int fuel) {
        
        this.fuel = fuel;
    }
    
    public int getFuelTotal() {
        
        return this.fuelTotal;
    }
    
    public void setFuelTotal(int fuelTotal) {
        
        this.fuelTotal = fuelTotal;
    }
    
    public boolean isProcessing() {
        
        return this.processing;
    }
    
    public int getProgress() {
        
        return this.progress;
    }
    
    public int getProgressMax() {
        
        return this.progressMax;
    }
    
    public PartType getLastType() {
        return lastType;
    }
    
    public void setLastPart(PartType lastType) {
        this.lastType = lastType;
    }
    
    public void setProcessing(boolean processing) {
        this.processing = processing;
    }
    
    public void setProgress(int progress) {
        this.progress = progress;
    }
    
    public void setLastType(PartType lastType) {
        this.lastType = lastType;
    }
}
