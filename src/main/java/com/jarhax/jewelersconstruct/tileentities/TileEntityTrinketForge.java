package com.jarhax.jewelersconstruct.tileentities;

import java.util.HashMap;
import java.util.Map;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.trinket.TrinketType;

import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityTrinketForge extends TileEntityBasicTickable {
    
    private final ItemStackHandler inventory;
    
    private TrinketType lastType = JewelryHelper.TRINKET_TYPES.getValues().get(0);
    
    public TileEntityTrinketForge() {
        
        this.inventory = new ItemStackHandler(5);
    }
    
    @Override
    public void onEntityUpdate () {
        
        boolean valid = true;
        if (this.inventory.getStackInSlot(4).isEmpty()) {
            
            for (int i = 0; i < this.getLastType().getPartTypes().length; i++) {
                final PartType type = this.getLastType().getPartTypes()[i];
                if (!this.getInventory().getStackInSlot(i).isEmpty()) {
                    if (this.getInventory().getStackInSlot(i).getItem() != type.getPartItem()) {
                        valid = false;
                    }
                }
                else {
                    valid = false;
                }
            }
            
            if (valid) {
                final ItemStack stack = new ItemStack(this.getLastType().getTrinketItem());
                final Map<Modifier, Integer> modifierMap = new HashMap<>();
                for (int i = 0; i < this.getInventory().getSlots(); i++) {
                    final ItemStack slot = this.getInventory().getStackInSlot(i);
                    
                    if (!slot.isEmpty()) {
                        final Map<Modifier, Integer> map = JewelryHelper.getModifiers(slot);
                        if (!map.isEmpty()) {
                            for (final Map.Entry<Modifier, Integer> entry : map.entrySet()) {
                                modifierMap.merge(entry.getKey(), entry.getValue(), (integer, integer2) -> integer + integer2);
                            }
                        }
                    }
                }
                
                JewelryHelper.setModifiers(stack, modifierMap);
                this.getInventory().setStackInSlot(4, stack);
            }
        }
    }
    
    @Override
    public void writeNBT (NBTTagCompound dataTag) {
        
        dataTag.setTag("inventory", this.inventory.serializeNBT());
        if (this.getLastType() != null) {
            dataTag.setString("lastPart", this.getLastType().getRegistryName().toString());
        }
    }
    
    @Override
    public void readNBT (NBTTagCompound dataTag) {
        
        this.inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
        this.lastType = JewelryHelper.getTrinketTypeByName(dataTag.getString("lastPart"));
    }
    
    public ItemStackHandler getInventory () {
        
        return this.inventory;
    }
    
    public TrinketType getLastType () {
        
        return this.lastType;
    }
    
    public void setLastType (TrinketType lastType) {
        
        this.lastType = lastType;
    }
}