package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.effect.ShootSpeedUp;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

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
                                final double[] mul = {1};
                                player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(shootSpeedBonus->
                                {

                                    mul[0] = shootSpeedBonus.getActual()+ mul[0];

                                });
                                MobEffect effect = TaotiesMunchies.SHOOT_SPEED_UP.get();
                                if (player.hasEffect(effect)) {
                                    mul[0] = mul[0] + (0.3*(player.getEffect(effect).getAmplifier()+1));
                                }
                                arrow.setDeltaMovement(arrow.getDeltaMovement().multiply(mul[0], mul[0], mul[0]));



                                mul[0] = 1;
                                player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(shootSpeedBonus->
                                {
                                    mul[0] = shootSpeedBonus.getActual()+mul[0];

                                });
                                effect = TaotiesMunchies.DMG_UP.get();
                                if (player.hasEffect(effect)) {
                                    mul[0] = mul[0] + (0.3*(player.getEffect(effect).getAmplifier()+1));
                                }
                                arrow.setBaseDamage(arrow.getBaseDamage()*mul[0]);

                            }
                            acc.setFoodAccelerated(true);

                        }
                    });
                }
            }











    }
}
