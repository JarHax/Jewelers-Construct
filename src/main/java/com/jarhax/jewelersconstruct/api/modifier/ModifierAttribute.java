package com.jarhax.jewelersconstruct.api.modifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jarhax.jewelersconstruct.TempUtils;

import net.darkhax.bookshelf.data.AttributeOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModifierAttribute extends Modifier {
    
    private final List<Multimap<String, AttributeModifier>> modifiers = new ArrayList<>();
    private final int maxLevel;
    
    public ModifierAttribute(String name, IAttribute attribute, double amount, AttributeOperation operation, String uuid) {
        
        this(name, attribute, amount, operation, 1, uuid);
    }
    
    public ModifierAttribute(String name, IAttribute attribute, double amount, AttributeOperation operation, int maxLevel, String uuid) {
        
        final ResourceLocation location = TempUtils.getIdForActiveMod(name);
        
        for (int i = 1; i <= maxLevel; i++) {
            
            final Multimap<String, AttributeModifier> levelModifiers = HashMultimap.create();
            levelModifiers.put(attribute.getName(), new AttributeModifier(UUID.fromString(uuid), location.getResourceDomain() + "_" + location.getResourcePath(), amount * i, operation.ordinal()));
            this.modifiers.add(levelModifiers);
        }
        
        this.maxLevel = maxLevel;
        this.setRegistryName(location);
    }
    
    @Override
    public void onWearerTick (ItemStack stack, EntityLivingBase user, int level) {
        
        if (!user.world.isRemote && user.ticksExisted % 600 == 0) {
            
            stack.damageItem(1, user);
        }
    }
    
    @Override
    public int getMaxLevel () {
        
        return this.maxLevel;
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers (ItemStack stack, EntityLivingBase wearer, int level) {
        
        return this.modifiers.get(this.getLevelInRange(level) - 1);
    }
}