package com.Cicadellidea.taotiesdelight.tracker;

import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerResistTracker {
    @SubscribeEvent
    public static void plyerGetDamage(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player)
        {
            if(!player.level().isClientSide)
            {
                event.setAmount(event.getAmount()/2);

            }
        }
    }
    @SubscribeEvent
    public static void playerLiving(PlayerEvent event)
    {
        Player player = event.getEntity();
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200,1));
    }
}
