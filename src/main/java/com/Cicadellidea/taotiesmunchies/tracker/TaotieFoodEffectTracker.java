package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TaotieFoodEffectTracker {
    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish event){
        if(TaotiesDelightConfig.effectBonus)
        {
            Item food = event.getItem().getItem();
            if(food.isEdible())
            {
                if(event.getEntity() instanceof Player player)
                {
                    if (!player.level().isClientSide)
                    {
                        if(TaotiesDelightConfig.foodSpeedBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,2400,1));
                        }
                        if(TaotiesDelightConfig.foodDamageBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,2400,0));
                        }
                        if(TaotiesDelightConfig.foodAttackSpeedBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,2400,1));
                        }
                        if(TaotiesDelightConfig.foodResistanceBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,2400,0));
                        }
                        if(TaotiesDelightConfig.foodHealingBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,2400,0));
                        }
                        if(TaotiesDelightConfig.foodSpeedBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(TaotiesMunchies.SHOOT_SPEED_UP.get(),2400,0));
                        }
                        if(TaotiesDelightConfig.foodArrowDamageBonus.contains(food))
                        {
                            player.addEffect(new MobEffectInstance(TaotiesMunchies.DMG_UP.get(),2400,0));
                        }

                    }
                }
            }
        }

    }
}
