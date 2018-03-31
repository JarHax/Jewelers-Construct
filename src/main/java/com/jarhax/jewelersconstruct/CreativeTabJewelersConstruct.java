package com.jarhax.jewelersconstruct;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabJewelersConstruct extends CreativeTabs {

    public CreativeTabJewelersConstruct() {
        
        super("jewelersconstruct");
    }

    @Override
    public ItemStack getTabIconItem () {
        
        return new ItemStack(Items.TOTEM_OF_UNDYING);
    }    
}
