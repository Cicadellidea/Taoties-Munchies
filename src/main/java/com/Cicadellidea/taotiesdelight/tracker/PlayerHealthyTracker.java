package com.Cicadellidea.taotiesdelight.tracker;

import com.Cicadellidea.taotiesdelight.Capabilites.PlayerFoodAttackSpeedBonusProvider;
import com.Cicadellidea.taotiesdelight.Capabilites.PlayerFoodHealingBonusProvider;
import com.Cicadellidea.taotiesdelight.Capabilites.PlayerFoodResistanceBonusProvider;
import com.Cicadellidea.taotiesdelight.TaotiesDelight;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.living.LivingDamageEvent;

import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TaotiesDelight.MODID)
public class PlayerHealthyTracker {
    @SubscribeEvent
    public static void playerGetDamage(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player)
        {
            if(!player.level().isClientSide)
            {
                player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus ->
                {
                    event.setAmount((float) (event.getAmount()/(1+bonus.getActual())));
                });


            }
        }
    }
    @SubscribeEvent
    public static void playerGetHealed(LivingHealEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player)
        {
            if(!player.level().isClientSide)
            {
                player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus ->
                {
                    event.setAmount((float) (event.getAmount()*(1+bonus.getActual())));
                });


            }
        }
    }






}
