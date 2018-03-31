package com.jarhax.jewelersconstruct.api.part;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PartType extends IForgeRegistryEntry.Impl<PartType> {
    
    private final Item baseItem;
    
    public PartType (Item item) {
        
        this.baseItem = item;
    }
    
    public Item getBaseItem() {
        
        return this.baseItem;
    }
    
    @SideOnly(Side.CLIENT)
    public String getTranslationName () {
        
        final ResourceLocation identifier = this.getRegistryName();
        return "jewelersconstruct.part." + identifier.getResourceDomain() + "." + identifier.getResourcePath();
    }
}