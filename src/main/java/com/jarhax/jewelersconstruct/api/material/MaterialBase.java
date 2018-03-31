package com.jarhax.jewelersconstruct.api.material;

public class MaterialBase extends Material {

    private final int durability;
    private final int retention;
    private final int purity;
    
    public MaterialBase(int durability, int retention, int purity, String name) {
        
        this.durability = durability;
        this.retention = retention;
        this.purity = purity;
        this.setRegistryName(name);
    }

    @Override
    int getDurability () {
        
        return this.durability;
    }

    @Override
    int getRetention () {
        
        return this.retention;
    }

    @Override
    int getPurity () {
        
        return this.purity;
    }   
}