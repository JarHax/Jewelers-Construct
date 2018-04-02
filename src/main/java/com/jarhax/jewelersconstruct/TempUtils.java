package com.jarhax.jewelersconstruct;

import java.util.HashSet;
import java.util.Set;

import net.darkhax.bookshelf.util.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
}
