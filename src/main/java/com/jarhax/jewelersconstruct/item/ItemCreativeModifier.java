package com.jarhax.jewelersconstruct.item;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.jarhax.jewelersconstruct.TempUtils;
import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;

import net.darkhax.bookshelf.item.IColorfulItem;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCreativeModifier extends Item implements IColorfulItem {

    public ItemCreativeModifier () {
        
        this.setMaxStackSize(1);
        this.hasSubtypes = true;
    }
    
    @Override
    public void getSubItems (CreativeTabs tab, NonNullList<ItemStack> items) {
        
        if (this.isInCreativeTab(tab)) {
            
            for (final Modifier modifier : JewelryHelper.MODIFIERS) {
                
                for (int level = 1; level <= modifier.getMaxLevel(); level++) {
                    
                    final ItemStack stack = new ItemStack(this);
                    JewelryHelper.setModifier(stack, modifier, level);
                    items.add(stack);
                }
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        
        final Set<Entry<Modifier, Integer>> modifiers = JewelryHelper.getModifiers(stack).entrySet();
        
        if (modifiers.isEmpty()) {
            
            tooltip.add(I18n.format("jewelersconstruct.modifier.missing"));
        }
        
        else {
            
            for (final Entry<Modifier, Integer> modifierData : modifiers) {
                
                tooltip.add(I18n.format(modifierData.getKey().getTranslationName()) + " " + I18n.format("enchantment.level." + modifierData.getValue()));
                TempUtils.getModifierTooltip(modifierData.getKey().getAttributeModifiers(stack, PlayerUtils.getClientPlayer(), modifierData.getValue()), tooltip);
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getColorHandler () {
        
        return (stack, layer) -> {
            
            Map<Modifier, Integer> modifiers = JewelryHelper.getModifiers(stack);
            
            int color = 0;
            
            for (Entry<Modifier, Integer> entry : modifiers.entrySet()) {
                
                Color awtColor = new Color(entry.getKey().getRegistryName().getResourcePath().hashCode());
                
                for (int level = 1; level != entry.getValue(); level++) {
                    
                    awtColor = TempUtils.darkenColor(awtColor, 0.86);
                }
                
                color += awtColor.getRGB();
            }
            
            return color;
        };
    }
}