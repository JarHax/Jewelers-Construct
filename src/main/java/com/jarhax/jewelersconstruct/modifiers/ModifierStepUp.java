package com.jarhax.jewelersconstruct.modifiers;

import com.jarhax.jewelersconstruct.api.modifier.ModifierBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ModifierStepUp extends ModifierBase {
    
    public ModifierStepUp() {
        super("step_up");
    }
    
    @Override
    public void onWearerTick(ItemStack stack, EntityLivingBase user, int level) {
        
        if(!user.world.isRemote && user.ticksExisted % 600 == 0) {
            
            stack.damageItem(1, user);
        }
        //TODO maybe change this to *add* a step height to the user, so it can be combined with other step assist methods
        user.stepHeight = 1;
        
        
    }
}
