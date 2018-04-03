package com.jarhax.jewelersconstruct.item;

import java.util.List;

import javax.annotation.Nullable;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.part.PartType;

import net.darkhax.bookshelf.item.IColorfulItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPart extends Item implements IColorfulItem {
    
    private final PartType type;
    
    public ItemPart(PartType type) {
        
        this.type = type;
        this.setMaxStackSize(1);
        this.hasSubtypes = true;
        type.setPartItem(this);
    }
    
    public PartType getPartType () {
        
        return this.type;
    }
    
    @Override
    public String getItemStackDisplayName (ItemStack stack) {
        
        return JewelryHelper.getMaterialName(JewelryHelper.getPartMaterial(stack)) + " " + I18n.format(this.getPartType().getTranslationName());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        
        tooltip.add(JewelryHelper.getMaterialName(JewelryHelper.getPartMaterial(stack)));
        tooltip.add("Modifier count: " +JewelryHelper.getPartMaterial(stack).getRetention());
        tooltip.add("Durability modifier: " +JewelryHelper.getPartMaterial(stack).getDurability());
    
    }
    
    @Override
    public void getSubItems (CreativeTabs tab, NonNullList<ItemStack> items) {
        
        if (this.isInCreativeTab(tab)) {
            
            for (final Material material : JewelryHelper.MATERIALS) {
                
                if (material.isValidForPart(this.getPartType()) && this.getPartType().isValidForMaterial(material)) {
                    
                    final ItemStack stack = new ItemStack(this);
                    JewelryHelper.setMaterial(stack, material);
                    items.add(stack);
                }
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getColorHandler () {
        
        return (stack, layer) -> {
            
            final Material material = JewelryHelper.getPartMaterial(stack);
            return material != null ? material.getColor(stack, layer) : 00;
        };
    }
}