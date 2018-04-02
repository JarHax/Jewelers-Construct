package com.jarhax.jewelersconstruct.api.modifier;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

public class ModifierKnockback extends Modifier {
    
    private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.fromString("3cfebf51-a271-4030-aacc-405afbe4d745"), "jewelersconstruct_modifier_knockback", 0.05, AttributeOperation.MULTIPLY.ordinal());
    
    public ModifierKnockback() {
        
        this.setRegistryName("knockback");
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        final Multimap<String, AttributeModifier> map = HashMultimap.create();
        
        map.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), MODIFIER);
        
        return map;
    }
}