package com.jarhax.jewelersconstruct.network;

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
        this.tile.setProcessing(processing);
        this.tile.setProgress(0);
    }
}