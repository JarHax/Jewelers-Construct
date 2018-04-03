package com.jarhax.jewelersconstruct.api.material;

import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.part.PartTypeGem;

public class MaterialGem extends MaterialBase {
    
    public MaterialGem(int durability, int retention, int purity, String name, int color) {
        
        super(durability, retention, purity, "gem" + name, color);
    }
    
    @Override
    public boolean isValidForPart (PartType type) {
        return type instanceof PartTypeGem;
    }
}