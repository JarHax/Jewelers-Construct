package com.jarhax.jewelersconstruct.modifiers;

import com.jarhax.jewelersconstruct.api.modifier.ModifierBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModifierSaturation extends ModifierBase {
    
    public ModifierSaturation() {
        super("saturation");
    }
    
    @Override
    public void onWearerTick(ItemStack stack, EntityLivingBase user, int level) {
        if(user instanceof EntityPlayer) {
            if(!user.world.isRemote && user.ticksExisted % 600 == 0) {
        
                stack.damageItem(1, user);
            }
            EntityPlayer player = (EntityPlayer) user;
            if(player.getFoodStats().getSaturationLevel()>10)
            player.getFoodStats().addStats(0, 1);
        }
        
    }
}
