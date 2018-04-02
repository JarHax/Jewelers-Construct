package com.jarhax.jewelersconstruct.network;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.trinket.TrinketType;
import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;

import net.darkhax.bookshelf.network.TileEntityMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class PacketSyncTrinketForge extends TileEntityMessage<TileEntityTrinketForge> {
    
    public ResourceLocation type;
    
    public PacketSyncTrinketForge() {
        
    }
    
    public PacketSyncTrinketForge(BlockPos pos, TrinketType type) {
        
        super(pos);
        this.type = type.getRegistryName();
    }
    
    @Override
    public void getAction () {
        
        final TrinketType type = JewelryHelper.TRINKET_TYPES.getValue(this.type);
        this.tile.setLastType(type);
        this.tile.getInventory().setStackInSlot(4, ItemStack.EMPTY);
    }
}