package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodArrowDamageBonus {

    private double foodArrowDamageBonus;
    private double limitBreakingStep;
    private double maxbonus;
    private double step;

    public PlayerFoodArrowDamageBonus()
    {
        this.foodArrowDamageBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxArrowDamageBonus;
        this.step = TaotiesDelightConfig.stepArrowDamageBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingArrowDamageBonusLimit;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodArrowDamageBonus, maxbonus);
    }

    public double increase()
    {

        foodArrowDamageBonus = foodArrowDamageBonus + step;
        return Math.min(foodArrowDamageBonus, maxbonus);
    }
    public void reset()
    {
        this.foodArrowDamageBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxArrowDamageBonus;
        this.step = TaotiesDelightConfig.stepArrowDamageBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingArrowDamageBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodArrowDamageBonus, maxbonus);
    }

    public void clone(PlayerFoodArrowDamageBonus bonus)
    {
        this.foodArrowDamageBonus = bonus.foodArrowDamageBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodArrowDamageBonus", foodArrowDamageBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodArrowDamageBonus = tag.getDouble("FoodArrowDamageBonus");
    }
}
