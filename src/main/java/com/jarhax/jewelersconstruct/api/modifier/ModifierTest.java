package com.jarhax.jewelersconstruct.api.modifier;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

public class ModifierTest extends Modifier {
    
    public static final AttributeModifier BUFF_TEST = new AttributeModifier(UUID.fromString("88f2f87f-c94e-4d1b-8bd2-2e686f26d50b"), "jewelersconstruct_modifier_debug", 100, AttributeOperation.ADDITIVE.ordinal());
    public ModifierTest() {
        
        this.setRegistryName("test");
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        final Multimap<String, AttributeModifier> map = HashMultimap.create();
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), BUFF_TEST);
        return map;
    }
    
    public int getModifiedDurability (ItemStack item, int base) {
        
        return 100;
    }
}
