package com.jarhax.jewelersconstruct.client.container;

import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotPartShaperFuel extends SlotItemHandler {
    
    private final TileEntityPartShaper tile;
    
    public SlotPartShaperFuel(TileEntityPartShaper tile, int index, int xPosition, int yPosition) {
        super(tile.getInventory(), index, xPosition, yPosition);
        this.tile = tile;
    }
    
    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack) > 0;
    }
}
