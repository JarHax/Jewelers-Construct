package com.jarhax.jewelersconstruct;

import com.jarhax.jewelersconstruct.api.Material;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.modifier.ModifierTest;
import com.jarhax.jewelersconstruct.blocks.BlockPartShaper;
import com.jarhax.jewelersconstruct.item.ItemJCon;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "jewelersconstruct", name = "Jewelers Construct", version = "@VERSION@")
@EventBusSubscriber
public class JewelersConstruct {
    
    public static final Logger LOG = LogManager.getLogger("Jewelers Construct");
    public static final RegistryHelper REGISTRY = new RegistryHelper().setTab(CreativeTabs.COMBAT).enableAutoRegistration();
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        LOG.info("Creating registries!");
        createForgeRegistry("modifiers", Modifier.class);
        createForgeRegistry("materials", Material.class);
        LOG.info("Registries created!");
        
        REGISTRY.registerItem(new ItemJCon(), "ring");
        REGISTRY.registerBlock(new BlockPartShaper(), "part_shaper");
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event) {
        
    }
    
    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        
    }
    
    @SubscribeEvent
    public static void onModifierRegister (RegistryEvent.Register<Modifier> event) {
        
        event.getRegistry().register(new ModifierTest());
    }
    
    private static <T extends IForgeRegistryEntry<T>> IForgeRegistry<T> createForgeRegistry (String name, Class<T> type) {
        
        return new RegistryBuilder<T>().setName(new ResourceLocation("jewelersconstruct", name)).setType(type).setMaxID(Integer.MAX_VALUE >> 5).create();
    }
}
