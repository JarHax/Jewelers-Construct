package com.jarhax.jewelersconstruct.api.material;

import net.minecraft.item.ItemStack;

public class MaterialBase extends Material {

    private final int durability;
    private final int retention;
    private final int purity;
    private final int color;
    
    public MaterialBase(int durability, int retention, int purity, String name, int color) {
        
        this.durability = durability;
        this.retention = retention;
        this.purity = purity;
        this.color = color;
        this.setRegistryName(name);
    }

    @Override
    public int getDurability () {
        
        return this.durability;
    }

    @Override
    public int getRetention () {
        
        return this.retention;
    }

    @Override
    public int getPurity () {
        
        return this.purity;
    }

    @Override
    public int getColor (ItemStack stack, int layer) {
        
        return this.color;
    }   
}