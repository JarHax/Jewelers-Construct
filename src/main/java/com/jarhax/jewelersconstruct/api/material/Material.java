package com.jarhax.jewelersconstruct.api.material;

import java.util.HashSet;
import java.util.Set;

import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.part.PartType;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Material extends IForgeRegistryEntry.Impl<Material> {
    
    /**
     * An empty set of traits. This is used as the default for {@link #getModifiers()}. New
     * entries should NEVER added here!!!
     */
    private static final Set<Modifier> EMPTY = new HashSet<>();
    
    /**
     * Gets the base durability for parts of this material.
     * 
     * @return The base durability.
     */
    public abstract int getDurability ();
    
    /**
     * Gets the amount of modifiers this part provides.
     * 
     * @return The amount of modifiers.
     */
    public abstract int getRetention ();
    
    /**
     * The purity of this material. This is used to determine what tier of modifiers can be
     * applied.
     * 
     * @return The purity of the material.
     */
    public abstract int getPurity ();
    
    /**
     * Gets the color to use for parts of this material.
     * 
     * @return The color of the material.
     */
    public abstract int getColor (ItemStack stack, int layer);
    
    /**
     * Checks if this material can be made into a part type.
     * 
     * @param type The type of part.
     * @return Whether or not it can be made.
     */
    public abstract boolean isValidForPart (PartType type);
    
    /**
     * Gets a set of all the modifiers this material provides by default.
     * 
     * @return The set of modifiers this material provides.
     */
    public Set<Modifier> getModifiers () {
        
        return EMPTY;
    }
    
    @SideOnly(Side.CLIENT)
    public String getTranslationName () {
        
        final ResourceLocation identifier = this.getRegistryName();
        return "jewelersconstruct.material." + identifier.getResourceDomain() + "." + identifier.getResourcePath();
    }
}