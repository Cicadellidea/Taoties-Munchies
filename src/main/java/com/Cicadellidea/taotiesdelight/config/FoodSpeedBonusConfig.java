package com.Cicadellidea.taotiesdelight.config;

import com.Cicadellidea.taotiesdelight.TaotiesDelight;
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

@Mod.EventBusSubscriber(modid = TaotiesDelight.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class FoodSpeedBonusConfig {
    private static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec.DoubleValue SPEED_BONUS_STEP = BUILDER.defineInRange("SpeedBonusStep",0.1,0,1000);
    private static ForgeConfigSpec.DoubleValue SPEED_BONUS_MAX= BUILDER.defineInRange("SpeedBonusMaximum",1d,0,1000);



    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FOOD_SPEED_BONUS = BUILDER.defineListAllowEmpty("SpeedBonusFood", List.of("minecraft:carrot"),FoodSpeedBonusConfig::allow);

    private static boolean allow(Object obj)
    {
        return ForgeRegistries.ITEMS.containsKey(new ResourceLocation((String) obj));
    }

    public static ForgeConfigSpec SPEC = BUILDER.build();

    public static double stepSpeedBonus, maxSpeedBonus;

    public static Set<Item> foodSpeedBonus;

    private static void getConfig()
    {
        stepSpeedBonus = SPEED_BONUS_STEP.get();
        maxSpeedBonus = SPEED_BONUS_MAX.get();
        foodSpeedBonus = FOOD_SPEED_BONUS.get().stream().map(itemname -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemname))).collect(Collectors.toSet());
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
