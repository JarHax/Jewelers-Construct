package com.jarhax.jewelersconstruct.api.modifier;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

public class ModifierArmor extends Modifier {
    
    private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.fromString("465eb56d-5480-4a1b-bcfc-600424748633"), "jewelersconstruct_modifier_armor", 2, AttributeOperation.ADDITIVE.ordinal());
    
    public ModifierArmor() {
        
        this.setRegistryName("armor");
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        final Multimap<String, AttributeModifier> map = HashMultimap.create();
        
        map.put(SharedMonsterAttributes.ARMOR.getName(), MODIFIER);
        
        return map;
    }
}