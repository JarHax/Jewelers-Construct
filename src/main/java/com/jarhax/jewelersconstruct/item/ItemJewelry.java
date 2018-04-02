package com.jarhax.jewelersconstruct.item;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.jarhax.jewelersconstruct.TempUtils;
import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.modifier.Modifier;
import com.jarhax.jewelersconstruct.api.trinket.TrinketType;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.darkhax.bookshelf.util.StackUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemJewelry extends Item implements IBauble {
    
    private static final String TAG_LAST_PLAYER = "LastPlayer";
    
    private final TrinketType type;
    private BaubleType baubleType;
    public ItemJewelry(TrinketType type,BaubleType baubleType) {
        
        this.setMaxStackSize(1);
        this.type = type;
        this.type.setTrinketItem(this);
        this.baubleType = baubleType;
    }
    
    @Override
    public void setDamage (ItemStack stack, int damage) {
        
        super.setDamage(stack, damage);
        
        if (stack.getItemDamage() > stack.getMaxDamage()) {
            
            super.setDamage(stack, stack.getMaxDamage());
        }
    }
    
    @Override
    public int getMaxDamage (ItemStack stack) {
        
        final int baseDurability = 1;
        
        int durability = 0;
        
        for (final Entry<Modifier, Integer> modifierData : JewelryHelper.getModifiers(stack).entrySet()) {
            
            durability += modifierData.getKey().getModifiedDurability(stack, modifierData.getValue(), baseDurability);
        }
        
        return durability;
    }
    
    @Override
    public BaubleType getBaubleType (ItemStack itemstack) {
        
        return baubleType;
    }
    
    @Override
    public void onWornTick (ItemStack stack, EntityLivingBase player) {
        
        if (!isLastUser(stack, player)) {
            
            this.onEquipped(stack, player);
        }
        
        JewelryHelper.tickJewelry(stack, player);
    }
    
    @Override
    public void onEquipped (ItemStack stack, EntityLivingBase player) {
        
        if (player instanceof EntityPlayer) {
            
            JewelryHelper.handleEquip(stack, (EntityPlayer) player);
            setLastUser(stack, player);
        }
    }
    
    @Override
    public void onUnequipped (ItemStack stack, EntityLivingBase player) {
        
        if (player instanceof EntityPlayer) {
            
            JewelryHelper.handleUnEquip(stack, (EntityPlayer) player);
        }
    }
    
    @Override
    public void getSubItems (CreativeTabs tab, NonNullList<ItemStack> items) {
        
        if (this.isInCreativeTab(tab)) {
            
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        
        final Set<Entry<Modifier, Integer>> modifiers = JewelryHelper.getModifiers(stack).entrySet();
        
        if (modifiers.isEmpty()) {
            
            tooltip.add(I18n.format("jewelersconstruct.modifier.missing"));
        }
        
        else {
            
            for (final Entry<Modifier, Integer> modifierData : modifiers) {
                
                tooltip.add(I18n.format(modifierData.getKey().getTranslationName()) + " " + I18n.format("enchantment.level." + modifierData.getValue()));
                TempUtils.getModifierTooltip(modifierData.getKey().getAttributeModifiers(stack, PlayerUtils.getClientPlayer(), modifierData.getValue()), tooltip);
            }
        }
        
        if (flagIn == ITooltipFlag.TooltipFlags.ADVANCED) {
            
            tooltip.add(I18n.format("tooltip.jewelersconstruct.modifiercout", JewelryHelper.getModifierCount(stack)));
        }
    }
    
    private static void setLastUser (ItemStack stack, EntityLivingBase user) {
        
        StackUtils.prepareStackTag(stack).setUniqueId(TAG_LAST_PLAYER, user.getUniqueID());
    }
    
    private static boolean isLastUser (ItemStack stack, EntityLivingBase user) {
        
        return stack.hasTagCompound() ? stack.getTagCompound().getUniqueId(TAG_LAST_PLAYER).equals(user.getUniqueID()) : false;
    }
}