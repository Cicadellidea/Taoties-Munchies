package com.Cicadellidea.taotiesdelight.Capabilites;

import com.Cicadellidea.taotiesdelight.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodAttackSpeedBonus {

    private double foodAttackSpeedBonus;
    private double limitBreakingStep;
    private double maxbonus;
    private double step;

    public PlayerFoodAttackSpeedBonus()
    {
        this.foodAttackSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxAttackSpeedBonus;
        this.step = TaotiesDelightConfig.stepAttackSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingAttackSpeedBonusLimit;
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
    }

    public void readNBT(CompoundTag tag)
    {
        foodAttackSpeedBonus = tag.getDouble("FoodAttackSpeedBonus");
    }
}
