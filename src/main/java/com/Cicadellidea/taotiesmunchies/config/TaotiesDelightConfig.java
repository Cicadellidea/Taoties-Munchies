package com.Cicadellidea.taotiesmunchies.config;

import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class TaotiesDelightConfig {
    private static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec.BooleanValue WHITE_LIST = BUILDER.define("WhiteList",false);
    private static ForgeConfigSpec.BooleanValue EFFECT_BONUS = BUILDER.define("GetTemporalEffectsAfterEating",false);
    private static ForgeConfigSpec.DoubleValue SPEED_BONUS_STEP = BUILDER.defineInRange("SpeedBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue SPEED_BONUS_MAX= BUILDER.defineInRange("SpeedBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue SPEED_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("SpeedBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SPEED_BONUS = BUILDER.defineListAllowEmpty("SpeedBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie","minecraft:bread"), TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SPEED_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("SpeedBonusLimitBreakingFood", List.of("minecraft:carrot"), TaotiesDelightConfig::allow);

    private static ForgeConfigSpec.DoubleValue ATTACK_SPEED_BONUS_STEP = BUILDER.defineInRange("AttackSpeedBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue ATTACK_SPEED_BONUS_MAX= BUILDER.defineInRange("AttackSpeedBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue ATTACK_SPEED_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("AttackSpeedBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_ATTACK_SPEED_BONUS = BUILDER.defineListAllowEmpty("AttackSpeedBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"),TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_ATTACK_SPEED_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("AttackSpeedBonusLimitBreakingFood", List.of("minecraft:carrot"),TaotiesDelightConfig::allow);


    public static double stepAttackSpeedBonus, maxAttackSpeedBonus,breakingAttackSpeedBonusLimit;

    public static Set<Item> foodAttackSpeedBonus, foodAttackSpeedBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue DAMAGE_BONUS_STEP = BUILDER.defineInRange("DamageBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue DAMAGE_BONUS_MAX= BUILDER.defineInRange("DamageBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue DAMAGE_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("DamageBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_DAMAGE_BONUS = BUILDER.defineListAllowEmpty("DamageBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"),TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_DAMAGE_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("DamageBonusLimitBreakingFood", List.of("minecraft:carrot"),TaotiesDelightConfig::allow);

    private static ForgeConfigSpec.DoubleValue RESISTANCE_BONUS_STEP = BUILDER.defineInRange("ResistanceBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue RESISTANCE_BONUS_MAX= BUILDER.defineInRange("ResistanceBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue RESISTANCE_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("ResistanceBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_RESISTANCE_BONUS = BUILDER.defineListAllowEmpty("ResistanceBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"),TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_RESISTANCE_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("ResistanceBonusLimitBreakingFood", List.of("minecraft:carrot"),TaotiesDelightConfig::allow);

    public static double stepDamageBonus, maxDamageBonus,breakingDamageBonusLimit;

    public static Set<Item> foodDamageBonus, foodDamageBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue HEALING_BONUS_STEP = BUILDER.defineInRange("HealingBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue HEALING_BONUS_MAX= BUILDER.defineInRange("HealingBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue HEALING_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("HealingBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_HEALING_BONUS = BUILDER.defineListAllowEmpty("HealingBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"),TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_HEALING_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("HealingBonusLimitBreakingFood", List.of("minecraft:carrot"),TaotiesDelightConfig::allow);

    public static double stepHealingBonus, maxHealingBonus,breakingHealingBonusLimit;

    public static Set<Item> foodHealingBonus, foodHealingBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue SHOOT_SPEED_BONUS_STEP = BUILDER.defineInRange("ShootSpeedBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue SHOOT_SPEED_BONUS_MAX= BUILDER.defineInRange("ShootSpeedBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue SHOOT_SPEED_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("ShootSpeedBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SHOOT_SPEED_BONUS = BUILDER.defineListAllowEmpty("ShootSpeedBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"),TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SHOOT_SPEED_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("ShootSpeedBonusLimitBreakingFood", List.of("minecraft:carrot"),TaotiesDelightConfig::allow);

    public static double stepShootSpeedBonus, maxShootSpeedBonus,breakingShootSpeedBonusLimit;

    public static Set<Item> foodShootSpeedBonus, foodShootSpeedBonusLimitBreaker;

    private static ForgeConfigSpec.DoubleValue ARROW_DAMAGE_BONUS_STEP = BUILDER.defineInRange("ArrowDamageBonusStep",0.025,0,1000);
    private static ForgeConfigSpec.DoubleValue ARROW_DAMAGE_BONUS_MAX= BUILDER.defineInRange("ArrowDamageBonusMaximum",1d,0,1000);
    private static ForgeConfigSpec.DoubleValue ARROW_DAMAGE_BONUS_LIMIT_BREAKING_STEP = BUILDER.defineInRange("ArrowDamageBonusLimitBreakingStep",0.1,0,1000);

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_ARROW_DAMAGE_BONUS = BUILDER.defineListAllowEmpty("ArrowDamageBonusFood", List.of("minecraft:carrot","minecraft:apple","minecraft:cookie"),TaotiesDelightConfig::allow);
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_ARROW_DAMAGE_BONUS_LIMIT_BREAKER = BUILDER.defineListAllowEmpty("ArrowDamageBonusLimitBreakingFood", List.of("minecraft:carrot"),TaotiesDelightConfig::allow);

    public static double stepArrowDamageBonus, maxArrowDamageBonus,breakingArrowDamageBonusLimit;

    public static Set<Item> foodArrowDamageBonus, foodArrowDamageBonusLimitBreaker;

    private static boolean allow(Object obj)
    {
        return ForgeRegistries.ITEMS.containsKey(new ResourceLocation((String) obj));
    }

    public static ForgeConfigSpec SPEC = BUILDER.build();

    public static double stepSpeedBonus, maxSpeedBonus,breakingSpeedBonusLimit;

    public static Set<Item> foodSpeedBonus, foodSpeedBonusLimitBreaker;


    public static double stepResistanceBonus, maxResistanceBonus,breakingResistanceBonusLimit;

    public static Set<Item> foodResistanceBonus, foodResistanceBonusLimitBreaker;

    public static boolean whiteList;
    public static boolean effectBonus;


    private static void getConfig()
    {
        whiteList = WHITE_LIST.get();
        effectBonus = EFFECT_BONUS.get();
        stepSpeedBonus = SPEED_BONUS_STEP.get();
        maxSpeedBonus = SPEED_BONUS_MAX.get();
        breakingSpeedBonusLimit = SPEED_BONUS_LIMIT_BREAKING_STEP.get();
        foodSpeedBonus = FOOD_SPEED_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodSpeedBonusLimitBreaker = FOOD_SPEED_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepDamageBonus = DAMAGE_BONUS_STEP.get();
        maxDamageBonus = DAMAGE_BONUS_MAX.get();
        breakingDamageBonusLimit = DAMAGE_BONUS_LIMIT_BREAKING_STEP.get();
        foodDamageBonus = FOOD_DAMAGE_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodDamageBonusLimitBreaker = FOOD_DAMAGE_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepAttackSpeedBonus = ATTACK_SPEED_BONUS_STEP.get();
        maxAttackSpeedBonus = ATTACK_SPEED_BONUS_MAX.get();
        breakingAttackSpeedBonusLimit = ATTACK_SPEED_BONUS_LIMIT_BREAKING_STEP.get();
        foodAttackSpeedBonus = FOOD_ATTACK_SPEED_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodAttackSpeedBonusLimitBreaker = FOOD_ATTACK_SPEED_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepResistanceBonus = RESISTANCE_BONUS_STEP.get();
        maxResistanceBonus = RESISTANCE_BONUS_MAX.get();
        breakingResistanceBonusLimit = RESISTANCE_BONUS_LIMIT_BREAKING_STEP.get();
        foodResistanceBonus = FOOD_RESISTANCE_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodResistanceBonusLimitBreaker = FOOD_RESISTANCE_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepHealingBonus = HEALING_BONUS_STEP.get();
        maxHealingBonus = HEALING_BONUS_MAX.get();
        breakingHealingBonusLimit = HEALING_BONUS_LIMIT_BREAKING_STEP.get();
        foodHealingBonus = FOOD_HEALING_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodHealingBonusLimitBreaker = FOOD_HEALING_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepShootSpeedBonus = SHOOT_SPEED_BONUS_STEP.get();
        maxShootSpeedBonus = SHOOT_SPEED_BONUS_MAX.get();
        breakingShootSpeedBonusLimit = SHOOT_SPEED_BONUS_LIMIT_BREAKING_STEP.get();
        foodShootSpeedBonus = FOOD_SHOOT_SPEED_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodShootSpeedBonusLimitBreaker = FOOD_SHOOT_SPEED_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());

        stepArrowDamageBonus = ARROW_DAMAGE_BONUS_STEP.get();
        maxArrowDamageBonus = ARROW_DAMAGE_BONUS_MAX.get();
        breakingArrowDamageBonusLimit = ARROW_DAMAGE_BONUS_LIMIT_BREAKING_STEP.get();
        foodArrowDamageBonus = FOOD_ARROW_DAMAGE_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
        foodArrowDamageBonusLimitBreaker = FOOD_ARROW_DAMAGE_BONUS_LIMIT_BREAKER.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());


    }





    @SubscribeEvent
    public static void onSetup(FMLCommonSetupEvent event)
    {
        getConfig();
    }
    @SubscribeEvent
    public static void onload(ModConfigEvent.Reloading event)
    {
        getConfig();
    }
}
