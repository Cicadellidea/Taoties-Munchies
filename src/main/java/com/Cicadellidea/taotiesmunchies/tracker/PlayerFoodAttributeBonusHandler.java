package com.Cicadellidea.taotiesmunchies.tracker;

import com.Cicadellidea.taotiesmunchies.TaotiesMunchies;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.UUID;
@Mod.EventBusSubscriber(modid = TaotiesMunchies.MODID)
public class PlayerFoodAttributeBonusHandler {
    private static final UUID TAOTIE_SPEED_MODIFIER_ID = UUID.fromString("bf6145e0-17bd-fe3c-3004-b036947ebf19");
    private static final UUID TAOTIE_DAMAGE_BONUS_MODIFIER_ID = UUID.fromString("654978f2-b858-5dc5-932f-4108b0e2ca3c");
    private static final UUID TAOTIE_ATTACK_SPEED_BONUS_MODIFIER_ID = UUID.fromString("bdfd9373-ba61-86f8-88b4-8fc65fdce578");
    private static final UUID TAOTIE_HEALTH_MODIFIER_ID = UUID.fromString("b5efb18c-ef5a-1a04-c7c9-37f37b6a6ac7");


    public static void updateTaotieSpeedModifier(Player player,double mul)
    {

        if (!player.level().isClientSide) {
            var attribute = Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED));

            AttributeModifier modifier = new AttributeModifier(
                    TAOTIE_SPEED_MODIFIER_ID,
                    "SpeedBonus Gained from Trying New Foods",
                    mul,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            attribute.removeModifier(modifier);
            attribute.addPermanentModifier(modifier);
        }
    }

    public static void updateTaotieDamageBonusModifier(Player player,double mul)
    {

        if (!player.level().isClientSide) {
            var attribute = Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE));

            AttributeModifier modifier = new AttributeModifier(
                    TAOTIE_DAMAGE_BONUS_MODIFIER_ID,
                    "DamageBonus Gained from Trying New Foods",
                    mul,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            attribute.removeModifier(modifier);
            attribute.addPermanentModifier(modifier);
        }
    }

    public static void updateTaotieAttackSpeedBonusModifier(Player player,double mul)
    {

        if (!player.level().isClientSide) {
            var attribute = Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED));

            AttributeModifier modifier = new AttributeModifier(
                    TAOTIE_ATTACK_SPEED_BONUS_MODIFIER_ID,
                    "AttackSpeedBonus Gained from Trying New Foods",
                    mul,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            attribute.removeModifier(modifier);
            attribute.addPermanentModifier(modifier);
        }
    }




}

