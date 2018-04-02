package com.jarhax.jewelersconstruct.tileentities;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.trinket.TrinketType;
import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

import java.util.*;
import java.util.function.BiFunction;

public class TileEntityTrinketForge extends TileEntityBasicTickable {
    
    private final ItemStackHandler inventory;
    
    private TrinketType lastType = JewelryHelper.TRINKET_TYPES.getValues().get(0);
    
    public TileEntityTrinketForge() {
        
        this.inventory = new ItemStackHandler(5);
    }
    
    @Override
    public void onEntityUpdate() {
        boolean valid = true;
        if(inventory.getStackInSlot(4).isEmpty()) {
    
            for(int i = 0; i < getLastType().getPartTypes().length; i++) {
                PartType type = getLastType().getPartTypes()[i];
                if(!getInventory().getStackInSlot(i).isEmpty()) {
                    if(getInventory().getStackInSlot(i).getItem() != type.getPartItem()) {
                        valid = false;
                    }
                } else {
                    valid = false;
                }
            }
    
            if(valid) {
                ItemStack stack = new ItemStack(getLastType().getTrinketItem());
                Map<Modifier, Integer> modifierMap = new HashMap<>();
                for(int i = 0; i < getInventory().getSlots(); i++) {
                    ItemStack slot = getInventory().getStackInSlot(i);
            
                    if(!slot.isEmpty()) {
                        Map<Modifier, Integer> map = JewelryHelper.getModifiers(slot);
                        if(!map.isEmpty()) {
                            for(Map.Entry<Modifier, Integer> entry : map.entrySet()) {
                                modifierMap.merge(entry.getKey(), entry.getValue(), (integer, integer2) -> integer + integer2);
                            }
                        }
                    }
                }
        
                JewelryHelper.setModifiers(stack, modifierMap);
                getInventory().setStackInSlot(4, stack);
            }
        }
    }
    
    @Override
    public void writeNBT(NBTTagCompound dataTag) {
        
        dataTag.setTag("inventory", this.inventory.serializeNBT());
        if(this.getLastType() != null) {
            dataTag.setString("lastPart", this.getLastType().getRegistryName().toString());
        }
    }
    
    @Override
    public void readNBT(NBTTagCompound dataTag) {
        
        this.inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
        this.lastType = JewelryHelper.getTrinketTypeByName(dataTag.getString("lastPart"));
    }
    
    public ItemStackHandler getInventory() {
        
        return this.inventory;
    }
    
    public TrinketType getLastType() {
        return lastType;
    }
    
    public void setLastType(TrinketType lastType) {
        this.lastType = lastType;
    }
}
