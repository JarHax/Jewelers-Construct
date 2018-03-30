package com.jarhax.jewelersconstruct.api;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class JewelersConstructRegistry {
    
    public static final IForgeRegistry<Modifier> MODIFIERS = GameRegistry.findRegistry(Modifier.class);
    public static final IForgeRegistry<Material> MATERIALS = GameRegistry.findRegistry(Material.class);
}