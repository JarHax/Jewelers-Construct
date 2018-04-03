package com.jarhax.jewelersconstruct.addons.tcon;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.material.MaterialBase;

import net.minecraftforge.registries.IForgeRegistry;

@CompatAddon(modid = "tconstruct")
public class AddonTcon extends Addon {
    
    public static final Material MATERIAL_ARDITE = new MaterialBase(260, 1, 1, "ardite", 0xd14210);
    public static final Material MATERIAL_COBALT = new MaterialBase(350, 1, 1, "cobalt", 0x2882d4);
    public static final Material MATERIAL_MANYULLYN = new MaterialBase(355, 1, 1, "manyullyn", 0xa15cf8);
    public static final Material MATERIAL_PIGIRON = new MaterialBase(300, 2, 3, "pigiron", 0xef9e9b);
    public static final Material MATERIAL_KNIGHTSLIME = new MaterialBase(285, 3, 3, "knightslime", 0xf18ff0);
    
    @Override
    public void registerMaterials(IForgeRegistry<Material> registry) {
        
        registry.register(MATERIAL_ARDITE);        
        registry.register(MATERIAL_COBALT);        
        registry.register(MATERIAL_MANYULLYN);        
        registry.register(MATERIAL_PIGIRON);  
        registry.register(MATERIAL_KNIGHTSLIME);
    }
    
    @Override
    public void associateMaterials() {
        
        JewelryHelper.associateMaterial("ingotArdite", MATERIAL_ARDITE);
        JewelryHelper.associateMaterial("ingotCobalt", MATERIAL_COBALT);
        JewelryHelper.associateMaterial("ingotManyullyn", MATERIAL_MANYULLYN);
        JewelryHelper.associateMaterial("ingotPigiron", MATERIAL_PIGIRON);
        JewelryHelper.associateMaterial("ingotKnightslime", MATERIAL_KNIGHTSLIME);
    }
}
