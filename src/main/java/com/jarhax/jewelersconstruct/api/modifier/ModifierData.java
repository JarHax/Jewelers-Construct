package com.jarhax.jewelersconstruct.api.modifier;

import com.jarhax.jewelersconstruct.api.JewelryHelper;

import net.minecraft.nbt.NBTTagCompound;

public class ModifierData {
    
    private static final String TAG_MODIFIER = "Modifier";
    private static final String TAG_LEVEL = "ModifierLevel";
    
    private final Modifier modifier;
    
    private int level = 0;
    
    public ModifierData(NBTTagCompound tag) {
        
        this(JewelryHelper.getModifierByName(tag.getString(TAG_MODIFIER)), tag.getInteger(TAG_LEVEL));
    }
    
    public ModifierData(Modifier modifier, int level) {
        
        this.modifier = modifier;
        this.level = level;
    }
    
    public Modifier getModifier () {
        
        return this.modifier;
    }
    
    public int getLevel () {
        
        return this.level;
    }
    
    public void setLevel (int level) {
        
        this.level = level;
    }
    
    public NBTTagCompound write () {
        
        final NBTTagCompound tag = new NBTTagCompound();
        
        tag.setString(TAG_MODIFIER, this.modifier == null ? "" : this.modifier.getRegistryName().toString());
        tag.setInteger(TAG_LEVEL, this.level);
        return tag;
    }
}