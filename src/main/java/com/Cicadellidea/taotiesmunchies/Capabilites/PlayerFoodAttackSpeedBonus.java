package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodAttackSpeedBonus {

    public double foodAttackSpeedBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodAttackSpeedBonus()
    {
        this.foodAttackSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxAttackSpeedBonus;
        this.step = TaotiesDelightConfig.stepAttackSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingAttackSpeedBonusLimit;
    }

    public void setbonus(double value)
    {
        foodAttackSpeedBonus = value;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodAttackSpeedBonus, maxbonus);
    }

    public double increase()
    {

        foodAttackSpeedBonus = foodAttackSpeedBonus + step;
        return Math.min(foodAttackSpeedBonus, maxbonus);
    }
    public void reset()
    {
        this.foodAttackSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxAttackSpeedBonus;
        this.step = TaotiesDelightConfig.stepAttackSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingAttackSpeedBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodAttackSpeedBonus, maxbonus);
    }

    public void clone(PlayerFoodAttackSpeedBonus bonus)
    {
        this.foodAttackSpeedBonus = bonus.foodAttackSpeedBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodAttackSpeedBonus", foodAttackSpeedBonus);
        tag.putDouble("maxAttackSpeedBonus",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodAttackSpeedBonus = tag.getDouble("FoodAttackSpeedBonus");
        maxbonus = tag.getDouble("maxAttackSpeedBonus");

    }
}
