package com.jarhax.jewelersconstruct.api;

import java.util.HashSet;
import java.util.Set;

import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.modifier.ModifierData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class JewelryHelper {
    
    public static final IForgeRegistry<Modifier> MODIFIERS = GameRegistry.findRegistry(Modifier.class);
    public static final IForgeRegistry<Material> MATERIALS = GameRegistry.findRegistry(Material.class);
    
    private static final String TAG_MODIFIERS = "Modifiers";
    
    public static Modifier getModifierByName (String name) {
        
        return MODIFIERS.getValue(new ResourceLocation(name));
    }
    
    public static Material getMaterialByName (String name) {
        
        return MATERIALS.getValue(new ResourceLocation(name));
    }
    
    public static void tickJewelry (ItemStack stack, EntityLivingBase user) {
        
        for (final ModifierData modifier : getModifiers(stack)) {
            
            modifier.getModifier().onWearerTick(stack, user, modifier.getLevel());
        }
    }
    
    public static void handleEquip (ItemStack stack, EntityLivingBase user) {
        
        for (final ModifierData modifier : getModifiers(stack)) {
            
            user.getAttributeMap().applyAttributeModifiers(modifier.getModifier().getAttributeModifiers(stack, user, modifier.getLevel()));
        }
    }
    
    public static void handleUnEquip (ItemStack stack, EntityLivingBase user) {
        
        for (final ModifierData modifier : getModifiers(stack)) {
            
            user.getAttributeMap().removeAttributeModifiers(modifier.getModifier().getAttributeModifiers(stack, user, modifier.getLevel()));
        }
    }
    
    public static Set<ModifierData> getModifiers (ItemStack stack) {
        
        final Set<ModifierData> modifiers = new HashSet<>();
        
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_MODIFIERS)) {
            
            final NBTTagList list = stack.getTagCompound().getTagList(TAG_MODIFIERS, NBT.TAG_COMPOUND);
            
            for (int i = 0; i < list.tagCount(); i++) {
                
                modifiers.add(new ModifierData(list.getCompoundTagAt(i)));
            }
        }
        
        return modifiers;
    }
}