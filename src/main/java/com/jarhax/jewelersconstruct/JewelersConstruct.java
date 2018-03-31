package com.jarhax.jewelersconstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jarhax.jewelersconstruct.api.Material;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.modifier.ModifierAttack;
import com.jarhax.jewelersconstruct.api.modifier.ModifierTest;
import com.jarhax.jewelersconstruct.blocks.BlockPartShaper;
import com.jarhax.jewelersconstruct.client.gui.GuiHandler;
import com.jarhax.jewelersconstruct.item.ItemJCon;
import com.jarhax.jewelersconstruct.proxy.CommonProxy;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(modid = "jewelersconstruct", name = "Jewelers Construct", version = "@VERSION@")
@EventBusSubscriber
public class JewelersConstruct {
    
    public static final Logger LOG = LogManager.getLogger("Jewelers Construct");
    public static final RegistryHelper REGISTRY = new RegistryHelper().setTab(CreativeTabs.COMBAT).enableAutoRegistration();
    
    @Mod.Instance("jewelersconstruct")
    public static JewelersConstruct INSTANCE;
    
    @SidedProxy(clientSide = "com.jarhax.jewelersconstruct.proxy.ClientProxy", serverSide = "com.jarhax.jewelersconstruct.proxy.CommonProxy")
    public static CommonProxy PROXY;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        LOG.info("Creating registries!");
        createForgeRegistry("modifiers", Modifier.class);
        createForgeRegistry("materials", Material.class);
        LOG.info("Registries created!");
        
        REGISTRY.registerItem(new ItemJCon(), "ring");
        GameRegistry.registerTileEntity(TileEntityPartShaper.class, "part_shaper");
        REGISTRY.registerBlock(new BlockPartShaper(), "part_shaper");
        
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event) {
        
        NetworkRegistry.INSTANCE.registerGuiHandler(JewelersConstruct.INSTANCE, new GuiHandler());
        PROXY.registerRenders();
    }
    
    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        
    }
    
    @SubscribeEvent
    public static void onModifierRegister (RegistryEvent.Register<Modifier> event) {
        
        event.getRegistry().register(new ModifierTest());
        event.getRegistry().register(new ModifierAttack());
    }
    
    private static <T extends IForgeRegistryEntry<T>> IForgeRegistry<T> createForgeRegistry (String name, Class<T> type) {
        
        return new RegistryBuilder<T>().setName(new ResourceLocation("jewelersconstruct", name)).setType(type).setMaxID(Integer.MAX_VALUE >> 5).create();
    }
}
