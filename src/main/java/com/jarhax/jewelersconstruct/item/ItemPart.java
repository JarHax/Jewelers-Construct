package com.jarhax.jewelersconstruct.item;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.part.PartType;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPart extends Item {
    
    private final PartType type;
    
    public ItemPart(PartType type) {
        
        this.type = type;        
        this.setMaxStackSize(1);
        this.hasSubtypes = true;
    }

    public PartType getPartType() {
        
        return this.type;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        
        return JewelryHelper.getMaterialName(JewelryHelper.getPartMaterial(stack)) + " " + I18n.format(this.getPartType().getTranslationName());
    }
}