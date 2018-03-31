package com.jarhax.jewelersconstruct;

import com.jarhax.jewelersconstruct.api.Material;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.modifier.ModifierAttack;
import com.jarhax.jewelersconstruct.api.modifier.ModifierTest;
import com.jarhax.jewelersconstruct.api.part.PartType;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class Content {
    
    // Modifiers
    public static final Modifier MODIFIER_TEST = new ModifierTest();
    public static final Modifier MODIFIER_ATTACK = new ModifierAttack();
    
    @SubscribeEvent
    public static void registerModifiers (RegistryEvent.Register<Modifier> event) {
        
        final IForgeRegistry<Modifier> registry = event.getRegistry();
        registry.register(MODIFIER_TEST);
        registry.register(MODIFIER_ATTACK);
    }
    
    // Materials
    @SubscribeEvent
    public static void registerMaterials (RegistryEvent.Register<Material> event) {
        
        final IForgeRegistry<Material> registry = event.getRegistry();
    }
    
    // Part Types
    @SubscribeEvent
    public static void registerPartTypes (RegistryEvent.Register<PartType> event) {
        
        final IForgeRegistry<PartType> registry = event.getRegistry();
    }
}