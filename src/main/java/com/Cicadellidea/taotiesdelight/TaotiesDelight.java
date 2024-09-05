package com.Cicadellidea.taotiesdelight;

import com.Cicadellidea.taotiesdelight.Capabilites.*;
import com.Cicadellidea.taotiesdelight.tracker.ArrowTracker;
import com.Cicadellidea.taotiesdelight.config.FoodSpeedBonusConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TaotiesDelight.MODID)
public class TaotiesDelight
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "taotiesdelight";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof Arrow arrow)
        {
            if(!arrow.getCapability(ArrowFoodAcceleratedProvider.ARROW_FOOD_ACCELERATED_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"arrowfoodaccelerated"),new ArrowFoodAcceleratedProvider());
            }
        }


        if(event.getObject() instanceof Player player)
        {
            if(!player.getCapability(PlayerFoodSpeedBonusProvider.PLAYER_FOOD_SPEED_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"foodspeedbonus"),new PlayerFoodSpeedBonusProvider());
            }
        }
        if(event.getObject() instanceof Player player)
        {
            if(!player.getCapability(PlayerFoodDamageBonusProvider.PLAYER_FOOD_DAMAGE_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"fooddamagebonus"),new PlayerFoodDamageBonusProvider());
            }
        }
        if(event.getObject() instanceof Player player)
        {
            if(!player.getCapability(PlayerFoodAttackSpeedBonusProvider.PLAYER_FOOD_ATTACK_SPEED_BONUS_CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MODID,"foodattackspeedbonus"),new PlayerFoodAttackSpeedBonusProvider());
            }
        }




    }

    public TaotiesDelight()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ArrowTracker());


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FoodSpeedBonusConfig.SPEC);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class,this::attachCapability);

    }


    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
//

    @SubscribeEvent
    public void onProjectHitEvent(ProjectileImpactEvent event)
    {


        if(event.getEntity() instanceof Arrow arrow)
        {
            if(arrow.getOwner() instanceof Player player)
            {
                if (!player.level().isClientSide)
                {
                    LOGGER.info(String.valueOf(player.getAttribute(Attributes.MOVEMENT_SPEED).getValue()));
                    LOGGER.info(String.valueOf(player.getAttribute(Attributes.ATTACK_SPEED).getValue()));
                    LOGGER.info(String.valueOf(player.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));






                }
            }
        }

    }




    @SubscribeEvent
    public static void registerCapability(RegisterCapabilitiesEvent event)
    {
        event.register(ArrowFoodAcceleratedProvider.class);
        event.register(PlayerFoodSpeedBonusProvider.class);
        event.register(PlayerFoodDamageBonusProvider.class);
        event.register(PlayerFoodAttackSpeedBonusProvider.class);



    }




    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent

}
