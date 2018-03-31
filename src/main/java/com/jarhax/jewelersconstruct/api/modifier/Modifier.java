package com.jarhax.jewelersconstruct.api.modifier;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Modifier extends IForgeRegistryEntry.Impl<Modifier> {
    
    /**
     * An empty Multimap of attribute modifiers. This is used as the default for
     * {@link #getAttributeModifiers(ItemStack, EntityLiving)}. New entries should NEVER added
     * here!!!
     */
    private static final Multimap<String, AttributeModifier> EMPTY = HashMultimap.create();
    
    /**
     * Gets the list of attribute modifiers that this trait provides.
     * 
     * @param stack The stack that the trait is being applied to.
     * @param wearer The wearer of the item.
     * @param level The level of the modifer.
     * @return A Multimap of all the attributes.
     */
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        return EMPTY;
    }
    
    /**
     * Gets the amount to modify the durability by. This will be added on to the original
     * value.
     * 
     * @param item The item being modified.
     * @param level The current level of the modifier.
     * @param base The base value.
     * @return The amount to modify the durability by.
     */
    public int getModifiedDurability (ItemStack item, int level, int base) {
        
        return 0;
    }
    
    /**
     * Gets the amount to modify the retention by. This will be added on to the original value.
     * 
     * @param item The item being modified.
     * @param level The current level of the modifier.
     * @param base The base value.
     * @return The amount to modify the retention by.
     */
    public int getModifiedRetention (ItemStack item, int level, int base) {
        
        return 0;
    }
    
    /**
     * Gets the amount to modify the purity by. This will be added on to the original value.
     * 
     * @param item The item being modified.
     * @param level The current level of the modifier.
     * @param base The base value.
     * @return The amount to modify the purity by.
     */
    public int getModifiedPurity (ItemStack item, int level, int base) {
        
        return 0;
    }
    
    /**
     * Gets the maximum amount of levels this modifier has.
     * 
     * @return The maximum level of this modifier;
     */
    public int getMaxLevel () {
        
        return 1;
    }
    
    /**
     * Checks if this modifier should count toward the total amount of modifiers on a piece of
     * jewelry.
     * 
     * @param stack The item being checked.
     * @param level The level of the modifier.
     * @return Whether or not this modifier counts towards the total modifier limit of a piece
     *         of jewelry.
     */
    public boolean countsTowardsLimit (ItemStack stack, int level) {
        
        return true;
    }
    
    /**
     * Called every tick when the player is wearing something with this modifier.
     * 
     * @param stack The item with the modifier.
     * @param user The player wearing it.
     * @param level The level of the modifier.
     */
    public void onWearerTick (ItemStack stack, EntityLivingBase user, int level) {
        
        // Default does nothing
    }
    
    /**
     * Gets the translation key for the modifiers name.
     * 
     * @return The translation key for the midifiers name.
     */
    @SideOnly(Side.CLIENT)
    public String getTranslationName () {
        
        final ResourceLocation identifier = this.getRegistryName();
        return "jewelersconstruct.modifier." + identifier.getResourceDomain() + "." + identifier.getResourcePath();
    }
    
    public int getLevelInRange (int level) {
        
        return Math.max(Math.min(level, this.getMaxLevel()), 0);
    }
}