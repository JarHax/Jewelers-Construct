package com.jarhax.jewelersconstruct.network;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import net.darkhax.bookshelf.network.TileEntityMessage;
import net.minecraft.util.math.BlockPos;

public class PacketStartPartShape extends TileEntityMessage<TileEntityPartShaper> {
    
    public boolean processing;
    
    public PacketStartPartShape() {
        
    }
    
    public PacketStartPartShape(BlockPos pos, boolean processing) {
        
        super(pos);
        this.processing = processing;
    }
    
    @Override
    public void getAction() {
        final Material material = JewelryHelper.getMaterial(tile.getInventory().getStackInSlot(0));
        if(material.isValidForPart(tile.getLastType())) {
            if(!this.tile.isProcessing()) {
                this.tile.setProgress(0);
            }
            this.tile.setProcessing(this.processing);
        }
        
    }
}