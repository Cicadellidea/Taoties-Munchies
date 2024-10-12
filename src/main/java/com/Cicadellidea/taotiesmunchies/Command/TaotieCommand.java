package com.Cicadellidea.taotiesmunchies.Command;


import com.Cicadellidea.taotiesmunchies.Capabilites.*;
import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import com.Cicadellidea.taotiesmunchies.tracker.PlayerFoodAttributeBonusHandler;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID)
public class TaotieCommand {

    @SubscribeEvent
    public static void registerChecker(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieAbilityCheck").executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();
            player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Damage:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Attack speed:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Speed:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Resistance:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Healing:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Shoot speed:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Arrow damage:"+String.format("%.4f",bonus.getActual())+"/"+String.format("%.4f",bonus.maxbonus)),false);});

            return 0;

        }));
    }

    @SubscribeEvent
    public static void registerClearer(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieAbilityReset").requires(p->{
            return p.hasPermission(2);
        }).executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();
            player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(PlayerFoodSpeedBonus::reset);
            PlayerFoodAttributeBonusHandler.updateTaotieSpeedModifier(player,0);
            player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(PlayerFoodAttackSpeedBonus::reset);
            PlayerFoodAttributeBonusHandler.updateTaotieAttackSpeedBonusModifier(player,0);
            player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(PlayerFoodDamageBonus::reset);
            PlayerFoodAttributeBonusHandler.updateTaotieDamageBonusModifier(player,0);
            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(PlayerFoodResistanceBonus::reset);
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(PlayerFoodHealingBonus::reset);
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(PlayerFoodShootSpeedBonus::reset);
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(PlayerFoodArrowDamageBonus::reset);
            player.getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).ifPresent(foodlist ->
            {
                foodlist.setFoodSet(new HashSet<>());

            });
            commandContext.getSource().sendSuccess(()->Component.literal("Ability cleared"),false);
            return 0;

        }));

    }

    @SubscribeEvent
    public static void registerDebugCommand(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieFoodListLog").requires(p->{
            return p.hasPermission(2);
        }).executes(commandContext -> {
            Objects.requireNonNull(commandContext.getSource().getPlayer()).getCapability(TaotiePlayerFoodListProvider.PLAYER_FOODL_LIST_CAPABILITY).ifPresent(list ->{
                TaotiesMunchies.LOGGER.info(String.valueOf(list.getFoodSet()));

            });
            return 0;
        }));
    }

    @SubscribeEvent
    public static void registerMaximize(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("TaotieAbilityMaximize").requires(p -> {
            return p.hasPermission(2);
        }).executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();
            player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {
                bonus.setbonus(1000);
            });

            commandContext.getSource().sendSuccess(() -> Component.literal("Success"), false);
            return 0;

        }));
    }
}
