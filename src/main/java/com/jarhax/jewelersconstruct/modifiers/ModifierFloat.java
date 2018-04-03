package com.jarhax.jewelersconstruct.modifiers;

import com.jarhax.jewelersconstruct.api.modifier.ModifierBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ModifierFloat extends ModifierBase {
    
    public ModifierFloat() {
        super("float");
    }
    
    @Override
    public void onWearerTick(ItemStack stack, EntityLivingBase user, int level) {
        if(!user.world.isRemote && user.ticksExisted % 600 == 0) {
            
            stack.damageItem(1, user);
        }
            if(!user.onGround && user.motionY < 0) {
                if(user.isSneaking()) {
                    user.motionY = -0.1;
                    user.fallDistance = -1;
                }
    
        }
        
        
    }
}
