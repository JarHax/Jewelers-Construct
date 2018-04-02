package com.jarhax.jewelersconstruct.api.trinket;

import net.minecraft.util.ResourceLocation;

public class TrinketTypeBase extends TrinketType {
    
    public TrinketTypeBase(String registryName, ResourceLocation iconLocation) {
        
        super(iconLocation);
        this.setRegistryName(registryName);
    }
}