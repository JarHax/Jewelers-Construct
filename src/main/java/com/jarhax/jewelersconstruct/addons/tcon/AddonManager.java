package com.jarhax.jewelersconstruct.addons.tcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.darkhax.bookshelf.util.AnnotationUtils;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

public class AddonManager {
    
    private static final List<Addon> features = new ArrayList<>();

    public static void init (ASMDataTable asmDataTable) {

        for (final Entry<Addon, CompatAddon> feature : AnnotationUtils.getAnnotations(asmDataTable, CompatAddon.class, Addon.class).entrySet()) {

            final CompatAddon annotation = feature.getValue();
            
            if (Loader.isModLoaded(annotation.modid())) {
                
                features.add(feature.getKey());
            }
        }
    }
    
    public static List<Addon> getAddons() {
        
        return features;
    }
}
