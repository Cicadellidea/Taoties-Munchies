package com.Cicadellidea.taotiesdelight.tracker;

import com.Cicadellidea.taotiesdelight.Capabilites.*;
import com.Cicadellidea.taotiesdelight.TaotiesDelight;
import com.Cicadellidea.taotiesdelight.config.TaotiesDelightConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;

@Mod.EventBusSubscriber(modid = TaotiesDelight.MODID)
public class PlayerFoodBonusingHandler
{
    @SubscribeEvent
    public static void playerFoodBonusingEvent(LivingEntityUseItemEvent.Finish event)
    {
        if(event.getEntity() instanceof Player player)
        {
            if(!player.level().isClientSide())
            {
                var item = event.getItem().getItem();
                if(item.isEdible())

                {

                    player.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).ifPresent(foodlist ->
                    {
                        if(foodlist.add(item))
                        {
                            if(TaotiesDelightConfig.foodSpeedBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(player, bonus.limintBreaking());
                                });
                            }
                            if(TaotiesDelightConfig.foodSpeedBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(player, bonus.increase());

                                });
                            }
                            if(TaotiesDelightConfig.foodDamageBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    PlayerFoodAttributeBonusHandler.updateTaotieDamageBonusModifier(player, bonus.limintBreaking());
                                });
                            }
                            if(TaotiesDelightConfig.foodDamageBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    PlayerFoodAttributeBonusHandler.updateTaotieDamageBonusModifier(player, bonus.increase());

                                });
                            }
                            if(TaotiesDelightConfig.foodAttackSpeedBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    PlayerFoodAttributeBonusHandler.updateTaotieAttackSpeedBonusModifier(player, bonus.limintBreaking());
                                });
                            }
                            if(TaotiesDelightConfig.foodAttackSpeedBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    PlayerFoodAttributeBonusHandler.updateTaotieAttackSpeedBonusModifier(player, bonus.increase());

                                });
                            }
                            if(TaotiesDelightConfig.foodResistanceBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(TaotiesDelightConfig.foodResistanceBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    bonus.increase();

                                });
                            }
                            if(TaotiesDelightConfig.foodHealingBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(TaotiesDelightConfig.foodHealingBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    bonus.increase();

                                });
                            }
                            if(TaotiesDelightConfig.foodShootSpeedBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(TaotiesDelightConfig.foodShootSpeedBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    bonus.increase();

                                });
                            }
                            if(TaotiesDelightConfig.foodArrowDamageBonusLimitBreaker.contains(item))
                            {
                                player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus->
                                {
                                    bonus.limintBreaking();
                                });
                            }
                            if(TaotiesDelightConfig.foodArrowDamageBonus.contains(item))
                            {
                                player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus ->
                                {

                                    bonus.increase();

                                });
                            }

                        }
                    });


                }




            }
        }
    }
    @SubscribeEvent
    public static void playerFoodBonusClone(PlayerEvent.Clone event)
    {
        Player origin = event.getOriginal();
        if(!origin.level().isClientSide)
        {
            origin.reviveCaps();


            var original = origin.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new);
            Player newPlayer = event.getEntity();
            var newInstance = newPlayer.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new);
            newInstance.clone(original);
            newPlayer.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
            {
                var mul = bonus.getActual();
                PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(newPlayer,mul);

            });

            newPlayer.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus->
            {
                var mul = bonus.getActual();
                PlayerFoodAttributeBonusHandler.updateTaotieDamageBonusModifier(newPlayer,mul);

            });
            newPlayer.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
            {
                var mul = bonus.getActual();
                PlayerFoodAttributeBonusHandler.updateTaotieAttackSpeedBonusModifier(newPlayer,mul);

            });
            newPlayer.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));
            newPlayer.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new).clone(origin.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).orElseThrow(RuntimeException::new));





            origin.invalidateCaps();


        }
    }

}
