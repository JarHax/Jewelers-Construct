package com.jarhax.jewelersconstruct.api.part;

import net.minecraft.util.ResourceLocation;

public class PartTypeBase extends PartType {

    public PartTypeBase(String registryName, ResourceLocation iconLocation) {
        
        super(iconLocation);
        this.setRegistryName(registryName);
    }   
}