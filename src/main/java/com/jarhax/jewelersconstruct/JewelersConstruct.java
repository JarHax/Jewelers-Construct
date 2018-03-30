package com.jarhax.jewelersconstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jarhax.jewelersconstruct.api.Material;
import com.jarhax.jewelersconstruct.api.Modifier;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(modid = "jewelersconstruct", name = "Jewelers Construct", version = "@VERSION@")
@EventBusSubscriber
public class JewelersConstruct {
    
    public static final Logger LOG = LogManager.getLogger("Jewelers Construct");
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        LOG.info("Creating registries!");
        createForgeRegistry("modifiers", Modifier.class);
        createForgeRegistry("materials", Material.class);
        LOG.info("Registries created!");
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event) {
        
    }
    
    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        
    }
    
    private static <T extends IForgeRegistryEntry<T>> IForgeRegistry<T> createForgeRegistry (String name, Class<T> type) {
        
        return new RegistryBuilder<T>().setName(new ResourceLocation("jewelersconstruct", name)).setType(type).setMaxID(Integer.MAX_VALUE >> 5).create();
    }
}
