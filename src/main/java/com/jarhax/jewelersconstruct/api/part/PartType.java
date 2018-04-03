package com.jarhax.jewelersconstruct.api.part;

import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.item.ItemPart;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PartType extends IForgeRegistryEntry.Impl<PartType> {
    
    private final ResourceLocation iconLocation;
    private ItemPart partItem;
    
    public ItemPart getPartItem () {
        
        return this.partItem;
    }
    
    public void setPartItem (ItemPart partItem) {
        
        this.partItem = partItem;
    }
    
    public PartType(ResourceLocation iconLocation) {
        
        this.iconLocation = iconLocation;
    }
    
    @SideOnly(Side.CLIENT)
    public String getTranslationName () {
        
        final ResourceLocation identifier = this.getRegistryName();
        return "jewelersconstruct.part." + identifier.getResourceDomain() + "." + identifier.getResourcePath();
    }
    
    @Deprecated
    public ResourceLocation getIconLocation () {
        
        return this.iconLocation;
    }
    
    public boolean isValidForMaterial (Material material) {
        
        return true;
    }
    
    public boolean hasDifferentMaterials() {
        
        return false;
    }
}