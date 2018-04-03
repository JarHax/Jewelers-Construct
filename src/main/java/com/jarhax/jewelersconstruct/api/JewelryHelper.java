package com.jarhax.jewelersconstruct.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Multimap;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.trinket.TrinketType;
import com.jarhax.jewelersconstruct.item.ItemJewelry;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.darkhax.bookshelf.lib.ItemStackMap;
import net.darkhax.bookshelf.util.StackUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class JewelryHelper {
    
    public static final IForgeRegistry<Modifier> MODIFIERS = GameRegistry.findRegistry(Modifier.class);
    public static final IForgeRegistry<Material> MATERIALS = GameRegistry.findRegistry(Material.class);
    public static final IForgeRegistry<PartType> PART_TYPES = GameRegistry.findRegistry(PartType.class);
    public static final IForgeRegistry<TrinketType> TRINKET_TYPES = GameRegistry.findRegistry(TrinketType.class);
    public static final ItemStackMap<Material> INPUTS_TO_MATERIALS = new ItemStackMap<>(ItemStackMap.SIMILAR);
    
    private static final String TAG_MODIFIERS = "Modifiers";
    private static final String TAG_MODIFIER = "Modifier";
    private static final String TAG_LEVEL = "ModifierLevel";
    private static final String TAG_MATERIAL = "Material";
    private static final String TAG_MATERIALS = "Materials";
    
    public static Modifier getModifierByName (String name) {
        
        return MODIFIERS.getValue(new ResourceLocation(name));
    }
    
    public static Material getMaterialByName (String name) {
        
        return MATERIALS.getValue(new ResourceLocation(name));
    }
    
    public static PartType getPartTypeByName (String name) {
        
        return PART_TYPES.getValue(new ResourceLocation(name));
    }
    
    public static TrinketType getTrinketTypeByName (String name) {
        
        return TRINKET_TYPES.getValue(new ResourceLocation(name));
    }
    
    public static void tickJewelry (ItemStack stack, EntityPlayer user) {
        
        if (!isBroken(stack)) {
            
            for (final Entry<Modifier, Integer> modifierData : getModifiers(stack).entrySet()) {
                
                modifierData.getKey().onWearerTick(stack, user, modifierData.getValue());
            }
        }
        
        else {
            
            updatePlayerModifiers(user);
        }
    }
    
    public static void updatePlayerModifiers (EntityPlayer player) {
        
        removeAllModifiers(player);
        
        for (final Entry<Modifier, Tuple<ItemStack, Integer>> topModifiers : getHighestModifiers(player).entrySet()) {
            
            player.getAttributeMap().applyAttributeModifiers(topModifiers.getKey().getAttributeModifiers(topModifiers.getValue().getFirst(), player, topModifiers.getValue().getSecond()));
        }
    }
    
    public static void removeAllModifiers(EntityPlayer player) {
        
        final IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
        
        for (int slot = 0; slot < baubles.getSlots(); slot++) {
            
            final ItemStack stack = baubles.getStackInSlot(slot);
            
            if (!stack.isEmpty() && stack.getItem() instanceof ItemJewelry) {
                
                for (final Entry<Modifier, Integer> modifierData : JewelryHelper.getModifiers(stack).entrySet()) {
                    
                    player.getAttributeMap().removeAttributeModifiers(modifierData.getKey().getAttributeModifiers(stack, player, modifierData.getValue()));
                }
            }
        }
    }
    
    public static Map<Modifier, Tuple<ItemStack, Integer>> getHighestModifiers (EntityPlayer player) {
        
        final IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
        final Map<Modifier, Integer> previousBest = new HashMap<>();
        final Map<Modifier, Tuple<ItemStack, Integer>> results = new HashMap<>();
        
        for (int slot = 0; slot < baubles.getSlots(); slot++) {
            
            final ItemStack stack = baubles.getStackInSlot(slot);
            
            if (!stack.isEmpty() && stack.getItem() instanceof ItemJewelry && !isBroken(stack)) {
                
                for (final Entry<Modifier, Integer> modifierData : JewelryHelper.getModifiers(stack).entrySet()) {
                    
                    final Modifier modifier = modifierData.getKey();
                    
                    // We are better than the last one, or there was no last one.
                    if (!previousBest.containsKey(modifier) || previousBest.get(modifier) < modifierData.getValue()) {
                        
                        results.put(modifier, new Tuple<>(stack, modifierData.getValue()));
                    }
                }
            }
        }
        
        return results;
    }
    
    public static void removeModifier (ItemStack stack, Modifier modifier) {
        
        final Map<Modifier, Integer> modifiers = getModifiers(stack);
        modifiers.remove(modifier);
        setModifiers(stack, modifiers);
    }
    
    public static void setModifier (ItemStack stack, Modifier modifier, int level) {
        
        final Map<Modifier, Integer> modifiers = getModifiers(stack);
        modifiers.put(modifier, level);
        setModifiers(stack, modifiers);
    }
    
    public static Map<Modifier, Integer> getModifiers (ItemStack stack) {
        
        final Map<Modifier, Integer> modifiers = new HashMap<>();
        
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_MODIFIERS)) {
            
            final NBTTagList list = stack.getTagCompound().getTagList(TAG_MODIFIERS, NBT.TAG_COMPOUND);
            
            for (int i = 0; i < list.tagCount(); i++) {
                
                final NBTTagCompound tag = list.getCompoundTagAt(i);
                final Modifier modifier = getModifierByName(tag.getString(TAG_MODIFIER));
                
                if (modifier != null) {
                    
                    modifiers.put(modifier, tag.getInteger(TAG_LEVEL));
                }
            }
        }
        
        return modifiers;
    }
    
    public static void setModifiers (ItemStack stack, Map<Modifier, Integer> modifiers) {
        
        final NBTTagList list = new NBTTagList();
        
        for (final Entry<Modifier, Integer> modifierData : modifiers.entrySet()) {
            
            final NBTTagCompound tag = new NBTTagCompound();
            tag.setString(TAG_MODIFIER, modifierData.getKey().getRegistryName().toString());
            tag.setInteger(TAG_LEVEL, modifierData.getValue());
            list.appendTag(tag);
        }
        
        StackUtils.prepareStackTag(stack).setTag(TAG_MODIFIERS, list);
    }
    
    public static int getModifierCount (ItemStack stack) {
        
        int count = 0;
        
        for (final Entry<Modifier, Integer> modifierData : getModifiers(stack).entrySet()) {
            
            if (modifierData.getKey().countsTowardsLimit(stack, modifierData.getValue())) {
                
                count += modifierData.getValue();
            }
        }
        
        return count;
    }
    
    public static Material getPartMaterial (ItemStack stack) {
        
        final NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.hasKey(TAG_MATERIAL) ? getMaterialByName(tag.getString(TAG_MATERIAL)) : null;
    }
    
    public static void setMaterial (ItemStack stack, Material material) {
        
        StackUtils.prepareStackTag(stack).setString(TAG_MATERIAL, material.getRegistryName().toString());
    }
    
    @SideOnly(Side.CLIENT)
    public static String getMaterialName (Material material) {
        
        final String translationKey = material != null ? material.getTranslationName() : "jewelersconstruct.material.undefined";
        return I18n.format(translationKey);
    }
    
    public static void associateMaterial (Item item, Material material) {
        
        associateMaterial(new ItemStack(item), material);
    }
    
    public static void associateMaterial (String oredict, Material material) {
        
        for (final ItemStack stack : OreDictionary.getOres(oredict)) {
            
            System.out.println("Associating " + stack.toString() + oredict);
            associateMaterial(stack, material);
        }
    }
    
    public static void associateMaterial (ItemStack stack, Material material) {
        
        INPUTS_TO_MATERIALS.put(stack, material);
    }
    
    public static Material getMaterial (ItemStack stack) {
        
        return INPUTS_TO_MATERIALS.get(stack);
    }
    
    public static ItemStack setJewelryMaterials(ItemStack stack, Collection<Material> materials) {
        
        final NBTTagCompound tag = StackUtils.prepareStackTag(stack);
        NBTTagList matList = new NBTTagList();
        
        for (Material material : materials) {
            
            matList.appendTag(new NBTTagString(material.getRegistryName().toString()));
        }
        
        tag.setTag(TAG_MATERIALS, matList);
        return stack;
    }
    
    public static void setJewelryMaterials(ItemStack stack, Material... materials) {
        
        final NBTTagCompound tag = StackUtils.prepareStackTag(stack);
        NBTTagList matList = new NBTTagList();
        
        for (Material material : materials) {
            
            matList.appendTag(new NBTTagString(material.getRegistryName().toString()));
        }
        
        tag.setTag(TAG_MATERIALS, matList);
    }
    
    public static List<Material> getJewelryMaterials(ItemStack stack) {
        
        final List<Material> materials = new ArrayList<>();
        
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_MATERIALS)) {
            
            final NBTTagList matList = stack.getTagCompound().getTagList(TAG_MATERIALS, NBT.TAG_STRING);
            
            for (int i = 0; i < matList.tagCount(); i++) {
                
                final Material mat = getMaterialByName(matList.getStringTagAt(i));
                
                if (mat != null) {
                    
                    materials.add(mat);
                }
            }
        }
        
        return materials;
    }
    
    public static List<Material> getValidMaterials(PartType partType) {
        
        final List<Material> validMaterials = new ArrayList<>();
        
        for (Material mat : MATERIALS) {
            
            if (partType.isValidForMaterial(mat) && mat.isValidForPart(partType)) {
                
                validMaterials.add(mat);
            }
        }
        
        return validMaterials;
    }
    
    public static boolean isBroken(ItemStack stack) {
        
        return stack.getMaxDamage() - stack.getItemDamage() <= 0;
    }
    
    public static int getRetention(ItemStack stack){
       return getJewelryMaterials(stack).stream().mapToInt(Material::getRetention).sum();
    }
    
}