package com.jarhax.jewelersconstruct.network;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.darkhax.bookshelf.network.TileEntityMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class PacketSyncPartShape extends TileEntityMessage<TileEntityPartShaper> {

    public ResourceLocation partType;

    public PacketSyncPartShape () {

    }

    public PacketSyncPartShape (BlockPos pos, PartType type) {

        super(pos);
        this.partType = type.getRegistryName();
    }

    @Override
    public void getAction () {

        final PartType type = JewelryHelper.PART_TYPES.getValue(this.partType);
        this.tile.setLastPart(type);
    }
}