package com.jarhax.jewelersconstruct.client.container;

import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class SlotPartShaperInput extends SlotItemHandler {
    
    private final TileEntityPartShaper tile;
    
    public SlotPartShaperInput(TileEntityPartShaper tile, int index, int xPosition, int yPosition) {
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return !tile.isProcessing();
    }
}
