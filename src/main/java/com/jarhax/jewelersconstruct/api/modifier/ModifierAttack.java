package com.jarhax.jewelersconstruct.api.modifier;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

public class ModifierAttack extends Modifier {
    
    public ModifierAttack() {
        
        this.setRegistryName("attack");
    }
    
    @Override
    public int getMaxLevel () {
        
        return 5;
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        final Multimap<String, AttributeModifier> map = HashMultimap.create();
        
        if (level > 0) {
            
            map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), Modifiers.values()[this.getLevelInRange(level - 1)].modifier);
        }
        
        return map;
    }
    
    enum Modifiers {
        
        LEVEL_1("9eba890b-d1e4-431f-bb93-1f72a1cdb2e3", 0.5f),
        LEVEL_2("078e21ba-62fe-44bd-82b7-a852a22a29ef", 1f),
        LEVEL_3("cee5bfae-6cdd-4d8e-aabe-63f9cb9103aa", 1.5f),
        LEVEL_4("70fbe5f1-f4dc-4c3e-9bf9-4db146def13c", 2f),
        LEVEL_5("dddd66ea-8953-471f-a247-e1d96d02d766", 2.5f);
        
        private final AttributeModifier modifier;
        
        Modifiers(String uuid, double amount) {
            
            this.modifier = new AttributeModifier(UUID.fromString(uuid), "jewelersconstruct_modifier_attack_" + amount, amount, AttributeOperation.ADDITIVE.ordinal());
        }
    }
}