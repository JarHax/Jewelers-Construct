package com.jarhax.jewelersconstruct;

import baubles.api.BaubleType;
import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.material.MaterialBase;
import com.jarhax.jewelersconstruct.api.material.MaterialGem;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.modifier.ModifierAttribute;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.part.PartTypeBase;
import com.jarhax.jewelersconstruct.api.part.PartTypeGem;
import com.jarhax.jewelersconstruct.api.trinket.*;
import com.jarhax.jewelersconstruct.blocks.BlockPartForge;
import com.jarhax.jewelersconstruct.blocks.BlockPartShaper;
import com.jarhax.jewelersconstruct.item.ItemCreativeModifier;
import com.jarhax.jewelersconstruct.item.ItemJCon;
import com.jarhax.jewelersconstruct.item.ItemPart;
import com.jarhax.jewelersconstruct.tileentities.*;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.OreDictUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class Content {
    
    /* ============================== Modifiers =========================== */
    public static final Modifier MODIFIER_VIGOR = new ModifierAttribute("vigor", SharedMonsterAttributes.ATTACK_DAMAGE, 1.5d, AttributeOperation.ADDITIVE, 5, "a9106b47-1153-4620-831e-38cb01a63fec");
    public static final Modifier MODIFIER_VITALITY = new ModifierAttribute("vitality", SharedMonsterAttributes.MAX_HEALTH, 2d, AttributeOperation.ADDITIVE, 5, "83ba3ce9-c4c5-472f-b9da-f79422e7d711");
    public static final Modifier MODIFIER_FORTITUDE = new ModifierAttribute("fortitude", SharedMonsterAttributes.ARMOR, 2d, AttributeOperation.ADDITIVE, 3, "00dc5641-1c03-4aa6-940c-d4195ca49d44c");
    public static final Modifier MODIFIER_STURDY = new ModifierAttribute("sturdy", SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 0.05d, AttributeOperation.MULTIPLY_SEPERATE, 3, "d770af89-3ec9-40f0-b36c-cbc67242adec");
    public static final Modifier MODIFIER_LUCK = new ModifierAttribute("luck", SharedMonsterAttributes.LUCK, 1d, AttributeOperation.ADDITIVE, 3, "142f0767-4c33-4afc-9d74-8990230b9ec7");
    public static final Modifier MODIFIER_GRASPING = new ModifierAttribute("grasping", EntityPlayer.REACH_DISTANCE, 1.25d, AttributeOperation.ADDITIVE, 3, "15b13705-586d-4b25-9fbf-7dc76613977b");
    public static final Modifier MODIFIER_SWIFT = new ModifierAttribute("swift", SharedMonsterAttributes.ATTACK_SPEED, 0.33d, AttributeOperation.ADDITIVE, 3, "0238fcd1-c252-4389-9022-06215adff31f");
    public static final Modifier MODIFIER_TRAVELER = new ModifierAttribute("traveler", SharedMonsterAttributes.MOVEMENT_SPEED, 0.1d, AttributeOperation.MULTIPLY, 5, "7a87405c-f8e8-4ba7-a235-fd6c0e90ebd2");
    
    @SubscribeEvent
    public static void registerModifiers (RegistryEvent.Register<Modifier> event) {
        
        final IForgeRegistry<Modifier> registry = event.getRegistry();
        registry.register(MODIFIER_VIGOR);
        registry.register(MODIFIER_VITALITY);
        registry.register(MODIFIER_FORTITUDE);
        registry.register(MODIFIER_STURDY);
        registry.register(MODIFIER_LUCK);
        registry.register(MODIFIER_GRASPING);
        registry.register(MODIFIER_SWIFT);
        registry.register(MODIFIER_TRAVELER);
    }
    
    /* ============================== Materials =========================== */
    public static final Material MATERIAL_WOOD = new MaterialBase(60, 1, 1, "wood", 0x755821);
    public static final Material MATERIAL_FLINT = new MaterialBase(70, 1, 2, "flint", 0x696969);
    public static final Material MATERIAL_BONE = new MaterialBase(80, 1, 1, "bone", 0xede6bf);
    public static final Material MATERIAL_STONE = new MaterialBase(120, 1, 1, "stone", 0x959595);
    public static final Material MATERIAL_IRON = new MaterialBase(256, 1, 1, "iron", 0xffffff);
    public static final Material MATERIAL_GOLD = new MaterialBase(60, 3, 2, "gold", 0xcabd3c);
    
    // Gems
    public static final Material MATERIAL_PRISMARINE = new MaterialGem(30, 0, 0, "Prismarine", 0x97c0b4);
    public static final Material MATERIAL_EMERALD = new MaterialGem(45, 0, 0, "Emerald", 0x35c765);
    public static final Material MATERIAL_DIAMOND = new MaterialGem(80, 0, 0, "Diamond", 0x74d9c7);
    
    // Common mod materials
    public static final Material MATERIAL_COPPER = new MaterialBase(260, 1, 1, "copper", 0xed9f07);
    public static final Material MATERIAL_BRONZE = new MaterialBase(350, 1, 1, "bronze", 0xe3bd68);
    public static final Material MATERIAL_LEAD = new MaterialBase(355, 1, 1, "lead", 0x4d4968);
    public static final Material MATERIAL_SILVER = new MaterialBase(300, 2, 3, "silver", 0xd1ecf6);
    public static final Material MATERIAL_ELECTRUM = new MaterialBase(285, 3, 3, "electrum", 0xe8db49);
    public static final Material MATERIAL_STEEL = new MaterialBase(500, 1, 1, "steel", 0xa7a7a7);
    
    @SubscribeEvent
    public static void registerMaterials (RegistryEvent.Register<Material> event) {
        
        final IForgeRegistry<Material> registry = event.getRegistry();
        registry.register(MATERIAL_WOOD);
        registry.register(MATERIAL_FLINT);
        registry.register(MATERIAL_BONE);
        registry.register(MATERIAL_STONE);
        registry.register(MATERIAL_IRON);
        registry.register(MATERIAL_GOLD);
        
        registry.register(MATERIAL_PRISMARINE);
        registry.register(MATERIAL_EMERALD);
        registry.register(MATERIAL_DIAMOND);
        
        // Common mod materials
        registry.register(MATERIAL_COPPER);
        registry.register(MATERIAL_BRONZE);
        registry.register(MATERIAL_LEAD);
        registry.register(MATERIAL_SILVER);
        registry.register(MATERIAL_ELECTRUM);
        registry.register(MATERIAL_STEEL);
    }
    
    public static void associateItemsToMaterial () {
        
        JewelryHelper.associateMaterial(OreDictUtils.PLANK_WOOD, MATERIAL_WOOD);
        JewelryHelper.associateMaterial(OreDictUtils.BONE, MATERIAL_BONE);
        JewelryHelper.associateMaterial(Items.FLINT, MATERIAL_FLINT);
        JewelryHelper.associateMaterial(OreDictUtils.STONE, MATERIAL_STONE);
        JewelryHelper.associateMaterial(OreDictUtils.INGOT_IRON, MATERIAL_IRON);
        JewelryHelper.associateMaterial("plateIron", MATERIAL_IRON);
        JewelryHelper.associateMaterial(OreDictUtils.INGOT_GOLD, MATERIAL_GOLD);
        JewelryHelper.associateMaterial("plateGold", MATERIAL_GOLD);
        
        // Common mod materials
        JewelryHelper.associateMaterial("ingotCopper", MATERIAL_COPPER);
        JewelryHelper.associateMaterial("plateCopper", MATERIAL_COPPER);
        JewelryHelper.associateMaterial("inogtBronze", MATERIAL_BRONZE);
        JewelryHelper.associateMaterial("plateBronze", MATERIAL_BRONZE);
        JewelryHelper.associateMaterial("ingotLead", MATERIAL_LEAD);
        JewelryHelper.associateMaterial("plateLead", MATERIAL_LEAD);
        JewelryHelper.associateMaterial("ingotSilver", MATERIAL_SILVER);
        JewelryHelper.associateMaterial("plateSilver", MATERIAL_SILVER);
        JewelryHelper.associateMaterial("ingotElectrum", MATERIAL_ELECTRUM);
        JewelryHelper.associateMaterial("plateElectrum", MATERIAL_ELECTRUM);
        JewelryHelper.associateMaterial("ingotSteel", MATERIAL_STEEL);
        JewelryHelper.associateMaterial("plateSteel", MATERIAL_STEEL);
    }
    
    /* ============================== Part Types ========================== */
    public static final PartType PART_BAND = new PartTypeBase("band", new ResourceLocation("jewelersconstruct", "textures/items/part_band.png"));
    public static final PartType PART_BINDING = new PartTypeBase("binding", new ResourceLocation("jewelersconstruct", "textures/items/part_binding.png"));
    public static final PartType PART_CHAIN = new PartTypeBase("chain", new ResourceLocation("jewelersconstruct", "textures/items/part_chain.png"));
    public static final PartType PART_BUCKLE = new PartTypeBase("buckle", new ResourceLocation("jewelersconstruct", "textures/items/part_buckle.png"));
    public static final PartType PART_GEM = new PartTypeGem("gem", new ResourceLocation("jewelersconstruct", "textures/items/part_gem.png"));
    
    @SubscribeEvent
    public static void registerPartTypes (RegistryEvent.Register<PartType> event) {
        
        final IForgeRegistry<PartType> registry = event.getRegistry();
        registry.register(PART_BAND);
        registry.register(PART_BINDING);
        registry.register(PART_CHAIN);
        registry.register(PART_BUCKLE);
        registry.register(PART_GEM);
    }
    
    /* ============================== Trinket Types ========================== */
    public static final TrinketType TYPE_RING = new TrinketTypeBase("ring", new ResourceLocation("jewelersconstruct", "textures/items/ring.png"));
    public static final TrinketType TYPE_BELT = new TrinketTypeBase("belt", new ResourceLocation("jewelersconstruct", "textures/items/belt.png"));
    
    
    @SubscribeEvent
    public static void registerTrinketTypes (RegistryEvent.Register<TrinketType> event) {
        
        final IForgeRegistry<TrinketType> registry = event.getRegistry();
        registry.register(TYPE_RING);
        TYPE_RING.setPartTypes(new PartType[]{PART_BAND, PART_BINDING, PART_GEM});
        registry.register(TYPE_BELT);
        TYPE_BELT.setPartTypes(new PartType[]{PART_BAND,PART_BAND, PART_BUCKLE, PART_GEM});
    }
    
    /* ============================== Blocks ============================== */
    public static final Block BLOCK_PART_SHAPER = new BlockPartShaper();
    public static final Block BLOCK_PART_FORGE = new BlockPartForge();
    
    public static void registerBlocks (RegistryHelper registry) {
        
        GameRegistry.registerTileEntity(TileEntityPartShaper.class, "part_shaper");
        GameRegistry.registerTileEntity(TileEntityTrinketForge.class, "trinket_forge");
    
        registry.registerBlock(BLOCK_PART_SHAPER, "part_shaper");
        registry.registerBlock(BLOCK_PART_FORGE, "part_forge");
        
    }
    
    /* ============================== Items =============================== */
    public static final Item ITEM_CREATIVE_MODIFIER = new ItemCreativeModifier();
    public static final Item ITEM_RING = new ItemJCon(TYPE_RING, BaubleType.RING); // TODO replace with specific items
    public static final Item ITEM_BELT = new ItemJCon(TYPE_BELT, BaubleType.BELT); // TODO replace with specific items
    
    public static final ItemPart ITEM_PART_BAND = new ItemPart(PART_BAND);
    public static final ItemPart ITEM_PART_BINDING = new ItemPart(PART_BINDING);
    public static final ItemPart ITEM_PART_CHAIN = new ItemPart(PART_CHAIN);
    public static final ItemPart ITEM_PART_BUCKLE = new ItemPart(PART_BUCKLE);
    public static final ItemPart ITEM_PART_GEM = new ItemPart(PART_GEM);
    
    public static void registerItems (RegistryHelper registry) {
        
        registry.registerItem(ITEM_RING, "ring");
        registry.registerItem(ITEM_BELT, "belt");
        registry.registerItem(ITEM_PART_BAND, "part_band");
        registry.registerItem(ITEM_PART_BINDING, "part_binding");
        registry.registerItem(ITEM_PART_CHAIN, "part_chain");
        registry.registerItem(ITEM_PART_BUCKLE, "part_buckle");
        registry.registerItem(ITEM_PART_GEM, "part_gem");
        registry.registerItem(ITEM_CREATIVE_MODIFIER, "creative_modifier");
    }
}