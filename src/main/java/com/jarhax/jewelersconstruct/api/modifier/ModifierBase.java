package com.jarhax.jewelersconstruct.api.modifier;

import com.jarhax.jewelersconstruct.TempUtils;
import net.minecraft.util.ResourceLocation;

public class ModifierBase extends Modifier {
    
    public ModifierBase(ResourceLocation location) {
        this.setRegistryName(location);
    }
    
    public ModifierBase(String location) {
        this.setRegistryName(TempUtils.getIdForActiveMod(location));
    }
}
