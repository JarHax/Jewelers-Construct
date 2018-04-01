package com.jarhax.jewelersconstruct.api.part;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PartType extends IForgeRegistryEntry.Impl<PartType> {
    private final ResourceLocation iconLocation;
    
    public PartType(ResourceLocation iconLocation) {
        this.iconLocation = iconLocation;
    }
    
    @SideOnly(Side.CLIENT)
    public String getTranslationName () {
        
        final ResourceLocation identifier = this.getRegistryName();
        return "jewelersconstruct.part." + identifier.getResourceDomain() + "." + identifier.getResourcePath();
    }
    
    public ResourceLocation getIconLocation() {
        return iconLocation;
    }
}