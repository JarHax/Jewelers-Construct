package com.jarhax.jewelersconstruct.api.part;

import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.material.MaterialGem;

import net.minecraft.util.ResourceLocation;

public class PartTypeGem extends PartTypeBase {
    
    public PartTypeGem(String registryName, ResourceLocation iconLocation) {
        
        super(registryName, iconLocation);
    }
    
    @Override
    public boolean isValidForMaterial (Material material) {
        
        return material instanceof MaterialGem;
    }
}