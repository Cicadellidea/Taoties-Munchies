package com.Cicadellidea.taotiesdelight.tracker;

import com.Cicadellidea.taotiesdelight.Capabilites.ArrowFoodAcceleratedProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ArrowTracker {
    @SubscribeEvent
    public void onArrowSpawn(EntityEvent event)
    {

        // Do something when the server starts
        Entity entity = event.getEntity();

            if (entity instanceof Arrow arrow)
            {
                if(!entity.level().isClientSide ) {
                    Entity owner = arrow.getOwner();
                    arrow.getCapability(ArrowFoodAcceleratedProvider.ARROW_FOOD_ACCELERATED_CAPABILITY).ifPresent(acc ->
                    {
                        if (!acc.getFoodAccelerated()) {


                            arrow.setDeltaMovement(arrow.getDeltaMovement().multiply(10, 10, 10));
                            arrow.setBaseDamage(arrow.getBaseDamage() + 2);
                            acc.setFoodAccelerated(true);
                        }
                    });
                }
            }









    }
}
