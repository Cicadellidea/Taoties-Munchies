package com.Cicadellidea.taotiesdelight.Command;


import com.Cicadellidea.taotiesdelight.Capabilites.*;
import com.Cicadellidea.taotiesdelight.TaotiesDelight;
import com.Cicadellidea.taotiesdelight.tracker.PlayerFoodAttributeBonusHandler;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;

@Mod.EventBusSubscriber(modid = TaotiesDelight.MODID)
public class TaotieCommand {

    @SubscribeEvent
    public static void registerChecker(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieAbilityCheck").executes(commandContext ->
        {
            Player player = commandContext.getSource().getPlayer();
            player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Speed:"+bonus.getActual()),false);});
            player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Attack speed:"+bonus.getActual()),false);});
            player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Damage:"+bonus.getActual()),false);});
            player.getCapability(PlayerFoodResistanceBonusProvider.PLAYER_FOOD_RESISTANCE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Resistance:"+bonus.getActual()),false);});
            player.getCapability(PlayerFoodHealingBonusProvider.PLAYER_FOOD_HEALING_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Healing:"+bonus.getActual()),false);});
            player.getCapability(PlayerFoodShootSpeedBonusProvider.PLAYER_FOOD_SHOOT_SPEED_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Shoot speed:"+bonus.getActual()),false);});
            player.getCapability(PlayerFoodArrowDamageBonusProvider.PLAYER_FOOD_ARROW_DAMAGE_BONUS_CAPABILITY).ifPresent(bonus -> {commandContext.getSource().sendSuccess(()->Component.literal("Arrow damage:"+bonus.getActual()),false);});

            return 0;

        }));
    }

    @SubscribeEvent
    public static void registerClearer(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TaotieAbilityReset").executes(commandContext ->
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
}
