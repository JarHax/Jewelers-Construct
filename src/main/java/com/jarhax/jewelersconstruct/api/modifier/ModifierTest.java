package com.jarhax.jewelersconstruct.api.modifier;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ModifierTest extends Modifier {
    
    public ModifierTest() {
        
        this.setRegistryName("test");
    }
    
    @Override
    public void onWearerTick (ItemStack item, EntityLivingBase user, int level) {
        
        System.out.println("Test");
    }
}
