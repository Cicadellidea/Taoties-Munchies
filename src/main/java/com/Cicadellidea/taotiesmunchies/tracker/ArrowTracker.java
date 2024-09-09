package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ArrowTracker {
    @SubscribeEvent
    public void onArrowSpawn(EntityEvent event)
    {

        // Do something when the server starts
        Entity entity = event.getEntity();

            if (entity instanceof AbstractArrow arrow)
            {
                if(!entity.level().isClientSide ) {
                    Entity owner = arrow.getOwner();
                    arrow.getCapability(ArrowFoodAcceleratedProvider.ARROW_FOOD_ACCELERATED_CAPABILITY).ifPresent(acc ->
                    {
                        if (!acc.getFoodAccelerated()) {
                            if(arrow.getOwner() instanceof  Player player)
                            {
                                player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(shootSpeedBonus->
                                {
                                    var mul = shootSpeedBonus.getActual()+1;
                                    arrow.setDeltaMovement(arrow.getDeltaMovement().multiply(mul, mul, mul));
                                });
                                player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(shootSpeedBonus->
                                {
                                    var mul = shootSpeedBonus.getActual()+1;
                                    arrow.setBaseDamage(arrow.getBaseDamage()*mul);
                                });

                            }
                            acc.setFoodAccelerated(true);

                        }
                    });
                }
            }











    }
}
