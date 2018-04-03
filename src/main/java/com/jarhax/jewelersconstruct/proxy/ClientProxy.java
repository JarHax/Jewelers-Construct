package com.jarhax.jewelersconstruct.proxy;

import com.jarhax.jewelersconstruct.client.render.RendertilePartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenders () {
        
        super.registerRenders();
        ClientRegistry.registerTileEntity(TileEntityPartShaper.class, "part_shaper", new RendertilePartShaper());
    }
    
    @Override
    public String translate(String str, Object... objects) {
        return I18n.format(str, objects);
    }
}
