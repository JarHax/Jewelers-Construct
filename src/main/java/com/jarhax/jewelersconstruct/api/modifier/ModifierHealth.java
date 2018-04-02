package com.jarhax.jewelersconstruct.api.modifier;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

public class ModifierHealth extends Modifier {
    
    private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.fromString("0e1747c6-9792-48f2-9e5b-9eca46d5aa07"), "jewelersconstruct_modifier_health", 2, AttributeOperation.ADDITIVE.ordinal());
    
    public ModifierHealth() {
        
        this.setRegistryName("health");
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        final Multimap<String, AttributeModifier> map = HashMultimap.create();
        
        map.put(SharedMonsterAttributes.MAX_HEALTH.getName(), MODIFIER);
        
        return map;
    }
}