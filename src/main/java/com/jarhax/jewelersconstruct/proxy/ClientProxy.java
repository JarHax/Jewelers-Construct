package com.jarhax.jewelersconstruct.proxy;

import com.jarhax.jewelersconstruct.client.render.RendertilePartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenders () {
        
        super.registerRenders();
        ClientRegistry.registerTileEntity(TileEntityPartShaper.class, "part_shaper", new RendertilePartShaper());
    }
}
