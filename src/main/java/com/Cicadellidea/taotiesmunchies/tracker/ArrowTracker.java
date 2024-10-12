package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.effect.ShootSpeedUp;
import com.ibm.icu.text.CaseMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ArrowTracker {
    @SubscribeEvent
    public void onArrowSpawn(EntityEvent event)
    {

        Entity entity = event.getEntity();

            if (entity instanceof AbstractArrow arrow)
            {
                if (arrow.tickCount == 0){
                    if(!entity.level().isClientSide ) {
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


                            /*
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
                            arrow.getCapability(ArrowFoodAcceleratedProvider.ARROW_FOOD_ACCELERATED_CAPABILITY).ifPresent(arrowFoodAccelerated ->
                            {
                                arrowFoodAccelerated.setFoodAccelerated(true);
                                arrow.getCapability(ArrowSpeedRecorderProvider.ARROW_SPEED_RECORDER_CAPABILITY).ifPresent(arrowSpeedRecorder -> {
                                    arrowSpeedRecorder.x = arrow.getDeltaMovement().x;
                                    arrowSpeedRecorder.y = arrow.getDeltaMovement().y;
                                    arrowSpeedRecorder.z = arrow.getDeltaMovement().z;

                                });
                            });

                             */

                        }
                    }
                }
            }
    }
    /*
    @SubscribeEvent
    public void  arrowHitting(ProjectileImpactEvent event)
    {
        Entity entity = event.getEntity();
        if(entity instanceof Arrow arrow)
        {
            TaotiesMunchies.LOGGER.info(String.valueOf(arrow.tickCount));
        }
    }

     */
/*
    @SubscribeEvent(priority = EventPriority.LOWEST)

    public void  arrowSwiming(EntityEvent event)
    {
        if(event.getEntity() instanceof  Arrow arrow)
        {
            if (!arrow.isInWater()){return;}
            arrow.getCapability(ArrowFoodAcceleratedProvider.ARROW_FOOD_ACCELERATED_CAPABILITY).ifPresent(arrowFoodAccelerated ->
            {
                if(arrowFoodAccelerated.getFoodAccelerated()){
                    if(!((arrow.getDeltaMovement().x==0)&&(arrow.getDeltaMovement().y==0)&&(arrow.getDeltaMovement().y==0)))
                    {
                        arrow.getCapability(ArrowSpeedRecorderProvider.ARROW_SPEED_RECORDER_CAPABILITY).ifPresent(speed ->{

                            double f = 0.99;
                            double dx = speed.x*f;
                            double dy = speed.y*f-0.05;
                            double dz = speed.z*f;
                            speed.x = dx;
                            speed.y = dy;
                            speed.z = dz;
                            arrow.setDeltaMovement(dx,dy,dz);
                            TaotiesMunchies.LOGGER.info(String.valueOf(dx));


                        });
                    }

                }
            });
        }
    }

 */
    @SubscribeEvent
    public void projectileHiting(LivingHurtEvent event){
        if(event.getSource().getDirectEntity() instanceof AbstractArrow Arrow){
            if(Arrow.getOwner() instanceof  Player player){
                if(!player.level().isClientSide){
                    final double[] mul = {1};

                    player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus->{

                        mul[0] += bonus.getActual();
                    });
                    MobEffect effect = TaotiesMunchies.DMG_UP.get();
                    if (player.hasEffect(effect)) {
                        mul[0] = mul[0] + (0.3*(player.getEffect(effect).getAmplifier()+1));
                    }

                    event.setAmount((float) (event.getAmount()*mul[0]));


                }
            }

        }
    }
}
