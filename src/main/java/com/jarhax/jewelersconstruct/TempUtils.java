package com.jarhax.jewelersconstruct;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Multimap;

import net.darkhax.bookshelf.util.StackUtils;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.oredict.OreDictionary;

public class TempUtils {
    
    public static boolean equalForCrafting (ItemStack stack, Object recipeStack) {
        
        if (recipeStack instanceof ItemStack) {
            
            return areStacksSimilarWithSize(stack, (ItemStack) recipeStack);
        }
        
        if (recipeStack instanceof String) {
            
            return getOreNames(stack).contains(recipeStack);
        }
        
        return false;
    }
    
    public static boolean areStacksSimilarWithSize (ItemStack firstStack, ItemStack secondStack) {
        
        return StackUtils.areStacksSimilar(firstStack, secondStack) && firstStack.getCount() >= secondStack.getCount();
    }
    
    public static Set<String> getOreNames (ItemStack stack) {
        
        final Set<String> names = new HashSet<>();
        
        // Why is the OreDictionary class so horrible :sob:
        for (final int id : OreDictionary.getOreIDs(stack)) {
            
            names.add(OreDictionary.getOreName(id));
        }
        
        return names;
    }
    
    public static ResourceLocation getIdForActiveMod (String name) {
        
        if (!name.contains(":")) {
            
            final ModContainer container = Loader.instance().activeModContainer();
            
            if (container != null) {
                
                return new ResourceLocation(container.getModId(), name);
            }
        }
        
        return new ResourceLocation(name);
    }
    
    public static void getModifierTooltip (Multimap<String, AttributeModifier> multimap, List<String> list) {
        
        if (!multimap.isEmpty()) {
            
            for (final Entry<String, AttributeModifier> entry : multimap.entries()) {
                
                final AttributeModifier modifier = entry.getValue();
                final double baseAmount = modifier.getAmount();
                
                double displayAmount = modifier.getOperation() != 1 && modifier.getOperation() != 2 ? baseAmount : baseAmount * 100d;
                
                if (baseAmount > 0.0D) {
                    
                    list.add(TextFormatting.BLUE + " " + JewelersConstruct.PROXY.translate("attribute.modifier.plus." + modifier.getOperation(), ItemStack.DECIMALFORMAT.format(displayAmount), JewelersConstruct.PROXY.translate("attribute.name." + entry.getKey())));
                }
                
                else if (baseAmount < 0.0D) {
                    
                    displayAmount = displayAmount * -1.0D;
                    list.add(TextFormatting.RED + " " + JewelersConstruct.PROXY.translate("attribute.modifier.take." + modifier.getOperation(), ItemStack.DECIMALFORMAT.format(displayAmount), JewelersConstruct.PROXY.translate("attribute.name." + entry.getKey())));
                }
            }
        }
    }
    
    public static Color darkenColor (Color awtColor, double factor) {
        
        return new Color(Math.max((int) (awtColor.getRed() * factor), 0), Math.max((int) (awtColor.getGreen() * factor), 0), Math.max((int) (awtColor.getBlue() * factor), 0), awtColor.getAlpha());
    }
    
    public static <T> Set<List<T>> getCombinations(List<List<T>> lists) {
        Set<List<T>> combinations = new HashSet<List<T>>();
        Set<List<T>> newCombinations;

        int index = 0;

        // extract each of the integers in the first list
        // and add each to ints as a new list
        for(T i: lists.get(0)) {
            List<T> newList = new ArrayList<T>();
            newList.add(i);
            combinations.add(newList);
        }
        index++;
        while(index < lists.size()) {
            List<T> nextList = lists.get(index);
            newCombinations = new HashSet<List<T>>();
            for(List<T> first: combinations) {
                for(T second: nextList) {
                    List<T> newList = new ArrayList<T>();
                    newList.addAll(first);
                    newList.add(second);
                    newCombinations.add(newList);
                }
            }
            combinations = newCombinations;

            index++;
        }

        return combinations;
    }
}
