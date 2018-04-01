package com.jarhax.jewelersconstruct;

import java.util.HashSet;
import java.util.Set;

import net.darkhax.bookshelf.util.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TempUtils {
    
    public static boolean equalForCrafting(ItemStack stack, Object recipeStack) {
        
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
    
    public static Set<String> getOreNames(ItemStack stack) {
        
        final Set<String> names = new HashSet<String>();
        
        // Why is the OreDictionary class so horrible :sob:
        for (int id : OreDictionary.getOreIDs(stack)) {
            
            names.add(OreDictionary.getOreName(id));
        }
        
        return names;
    }
}
