package com.Cicadellidea.taotiesdelight.Capabilites;

import com.Cicadellidea.taotiesdelight.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodShootSpeedBonus {

    private double foodShootSpeedBonus;
    private double limitBreakingStep;
    private double maxbonus;
    private double step;

    public PlayerFoodShootSpeedBonus()
    {
        this.foodShootSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxShootSpeedBonus;
        this.step = TaotiesDelightConfig.stepShootSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingShootSpeedBonusLimit;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodShootSpeedBonus, maxbonus);
    }

    public double increase()
    {

        foodShootSpeedBonus = foodShootSpeedBonus + step;
        return Math.min(foodShootSpeedBonus, maxbonus);
    }
    public void reset()
    {
        this.foodShootSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxShootSpeedBonus;
        this.step = TaotiesDelightConfig.stepShootSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingShootSpeedBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodShootSpeedBonus, maxbonus);
    }

    public void clone(PlayerFoodShootSpeedBonus bonus)
    {
        this.foodShootSpeedBonus = bonus.foodShootSpeedBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodShootSpeedBonus", foodShootSpeedBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodShootSpeedBonus = tag.getDouble("FoodShootSpeedBonus");
    }
}
