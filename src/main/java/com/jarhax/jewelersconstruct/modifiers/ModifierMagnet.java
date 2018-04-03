package com.jarhax.jewelersconstruct.modifiers;

import com.jarhax.jewelersconstruct.api.modifier.ModifierBase;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ModifierMagnet extends ModifierBase {
    
    public ModifierMagnet() {
        super("magnet");
    }
    
    @Override
    public void onWearerTick(ItemStack stack, EntityLivingBase user, int level) {
        
        if(!user.world.isRemote && user.ticksExisted % 600 == 0) {
            
            stack.damageItem(1, user);
        }
        if(!user.world.isRemote) {
            List<EntityItem> list = user.world.getEntitiesWithinAABB(EntityItem.class, user.getEntityBoundingBox().grow(33, 3, 3));
            for(EntityItem item : list) {
                item.setPosition(user.posX, user.posY, user.posZ);
            }
        }
        
    }
}
