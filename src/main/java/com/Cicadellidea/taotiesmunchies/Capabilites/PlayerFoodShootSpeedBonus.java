package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodShootSpeedBonus {

    public double foodShootSpeedBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodShootSpeedBonus()
    {
        this.foodShootSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxShootSpeedBonus;
        this.step = TaotiesDelightConfig.stepShootSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingShootSpeedBonusLimit;
    }

    public void setbonus(double value)
    {
        foodShootSpeedBonus = value;
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
        tag.putDouble("maxShootSpeedBonus",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodShootSpeedBonus = tag.getDouble("FoodShootSpeedBonus");
        maxbonus = tag.getDouble("maxShootSpeedBonus");
    }
}
