package com.jarhax.jewelersconstruct.item;

import com.jarhax.jewelersconstruct.api.JewelryHelper;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemJCon extends Item implements IBauble {
    
    @Override
    public BaubleType getBaubleType (ItemStack itemstack) {
        
        return BaubleType.CHARM;
    }
    
    @Override
    public void onWornTick (ItemStack stack, EntityLivingBase player) {
        
        JewelryHelper.tickJewelry(stack, player);
    }
    
    @Override
    public void onEquipped (ItemStack stack, EntityLivingBase player) {
        
        JewelryHelper.handleEquip(stack, player);
    }
    
    @Override
    public void onUnequipped (ItemStack stack, EntityLivingBase player) {
        
        JewelryHelper.handleUnEquip(stack, player);
    }
}