package com.Cicadellidea.taotiesdelight.tracker;

import com.Cicadellidea.taotiesdelight.Capabilites.PlayerFoodSpeedBonusProvider;
import com.Cicadellidea.taotiesdelight.TaotiesDelight;
import com.Cicadellidea.taotiesdelight.config.FoodSpeedBonusConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
                    if(FoodSpeedBonusConfig.foodSpeedBonus.contains(item)) {
                        player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus ->
                        {
                            var mul = bonus.increase();
                            PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(player, mul);

                        });
                    }
                }

                if(player.isSwimming())
                {
                    player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
                    {
                        bonus.setFoodSpeedBonus(0);
                        PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(player,0);

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
            newInstance.setFoodSpeedBonus(original.getFoodSpeedBonus());
            origin.invalidateCaps();

            newPlayer.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus->
            {
                var mul = bonus.increase();
                PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(newPlayer,mul);

            });


        }
    }

}
