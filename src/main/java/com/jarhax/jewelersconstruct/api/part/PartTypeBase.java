package com.jarhax.jewelersconstruct.api.part;

import net.minecraft.item.Item;

public class PartTypeBase extends PartType {

    public PartTypeBase(String registryName, Item item) {
        
        super(item);
        this.setRegistryName(registryName);
    }   
}