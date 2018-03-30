package com.jarhax.jewelersconstruct.api;

import java.util.HashSet;
import java.util.Set;

import com.jarhax.jewelersconstruct.api.modifier.Modifier;

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
    abstract int getDurability ();
    
    /**
     * Gets the amount of modifiers this part provides.
     * 
     * @return The amount of modifiers.
     */
    abstract int getRetention ();
    
    /**
     * The purity of this material. This is used to determine what tier of modifiers can be
     * applied.
     * 
     * @return The purity of the material.
     */
    abstract int getPurity ();
    
    /**
     * Gets a set of all the modifiers this material provides by default.
     * 
     * @return The set of modifiers this material provides.
     */
    public Set<Modifier> getModifiers () {
        
        return EMPTY;
    }
}