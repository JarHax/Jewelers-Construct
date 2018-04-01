package com.jarhax.jewelersconstruct;

import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.material.MaterialBase;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.modifier.ModifierAttack;
import com.jarhax.jewelersconstruct.api.modifier.ModifierTest;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.part.PartTypeBase;
import com.jarhax.jewelersconstruct.blocks.BlockPartForge;
import com.jarhax.jewelersconstruct.blocks.BlockPartShaper;
import com.jarhax.jewelersconstruct.item.ItemJCon;
import com.jarhax.jewelersconstruct.item.ItemPart;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class Content {
    
    /* ============================== Modifiers =========================== */
    public static final Modifier MODIFIER_TEST = new ModifierTest();
    public static final Modifier MODIFIER_ATTACK = new ModifierAttack();
    
    @SubscribeEvent
    public static void registerModifiers (RegistryEvent.Register<Modifier> event) {
        
        final IForgeRegistry<Modifier> registry = event.getRegistry();
        registry.register(MODIFIER_TEST);
        registry.register(MODIFIER_ATTACK);
    }
    
    public static final Material MATERIAL_WOOD = new MaterialBase(60, 1, 1, "wood");
    public static final Material MATERIAL_BONE = new MaterialBase(80, 1, 1, "bone");
    public static final Material MATERIAL_STONE = new MaterialBase(120, 1, 1, "stone");
    public static final Material MATERIAL_IRON = new MaterialBase(256, 1, 1, "iron");
    public static final Material MATERIAL_GOLD = new MaterialBase(60, 3, 2, "gold");
    
    /* ============================== Materials =========================== */
    @SubscribeEvent
    public static void registerMaterials (RegistryEvent.Register<Material> event) {
        
        final IForgeRegistry<Material> registry = event.getRegistry();
        registry.register(MATERIAL_WOOD);
        registry.register(MATERIAL_BONE);
        registry.register(MATERIAL_STONE);
        registry.register(MATERIAL_IRON);
        registry.register(MATERIAL_GOLD);
    }
    
    public static final PartType PART_BAND = new PartTypeBase("band");
    public static final PartType PART_BINDING = new PartTypeBase("binding");
    public static final PartType PART_CHAIN = new PartTypeBase("chain");
    public static final PartType PART_BUCKLE = new PartTypeBase("buckle");
    
    /* ============================== Part Types ========================== */
    @SubscribeEvent
    public static void registerPartTypes (RegistryEvent.Register<PartType> event) {
        
        final IForgeRegistry<PartType> registry = event.getRegistry();
        registry.register(PART_BAND);
        registry.register(PART_BINDING);
        registry.register(PART_CHAIN);
        registry.register(PART_BUCKLE);
    }
    
    /* ============================== Blocks ============================== */
    public static final Block BLOCK_PART_SHAPER = new BlockPartShaper();
    public static final Block BLOCK_PART_FORGE = new BlockPartForge();
    
    
    public static void registerBlocks (RegistryHelper registry) {
        
        GameRegistry.registerTileEntity(TileEntityPartShaper.class, "part_shaper");
        registry.registerBlock(BLOCK_PART_SHAPER, "part_shaper");
        registry.registerBlock(BLOCK_PART_FORGE, "part_forge");
    
    }
    
    /* ============================== Items =============================== */
    public static final Item ITEM_RING = new ItemJCon(); // TODO replace with specific items
    public static final ItemPart ITEM_PART_BAND = new ItemPart(PART_BAND);
    public static final ItemPart ITEM_PART_BINDING = new ItemPart(PART_BINDING);
    public static final ItemPart ITEM_PART_CHAIN = new ItemPart(PART_CHAIN);
    public static final ItemPart ITEM_PART_BUCKLE = new ItemPart(PART_BUCKLE);
    
    public static void registerItems (RegistryHelper registry) {
        
        registry.registerItem(ITEM_RING, "ring");
        registry.registerItem(ITEM_PART_BAND, "part_band");
        registry.registerItem(ITEM_PART_BINDING, "part_binding");
        registry.registerItem(ITEM_PART_CHAIN, "part_chain");
        registry.registerItem(ITEM_PART_BUCKLE, "part_buckle");
    }
}