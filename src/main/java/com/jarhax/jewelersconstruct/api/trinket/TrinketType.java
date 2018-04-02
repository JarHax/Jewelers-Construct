package com.jarhax.jewelersconstruct.api.trinket;

import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.item.ItemJewelry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class TrinketType extends IForgeRegistryEntry.Impl<TrinketType> {
    
    private final ResourceLocation iconLocation;
    private ItemJewelry trinketItem;
    private PartType[] partTypes;
    
    public TrinketType(ResourceLocation iconLocation) {
        
        this.iconLocation = iconLocation;
    }
    
    @SideOnly(Side.CLIENT)
    public String getTranslationName () {
        
        final ResourceLocation identifier = this.getRegistryName();
        return "jewelersconstruct.trinket." + identifier.getResourceDomain() + "." + identifier.getResourcePath();
    }
    
    @Deprecated
    public ResourceLocation getIconLocation () {
        
        return this.iconLocation;
    }
    
    public ItemJewelry getTrinketItem () {
        
        return this.trinketItem;
    }
    
    public void setTrinketItem (ItemJewelry trinketItem) {
        
        this.trinketItem = trinketItem;
    }
    
    public PartType[] getPartTypes () {
        
        return this.partTypes;
    }
    
    public void setPartTypes (PartType[] partTypes) {
        
        this.partTypes = partTypes;
    }
}
