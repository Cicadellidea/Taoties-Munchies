package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodSpeedBonus {

    private double foodSpeedBonus;
    private double limitBreakingStep;
    private double maxbonus;
    private double step;

    public PlayerFoodSpeedBonus()
    {
        this.foodSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxSpeedBonus;
        this.step = TaotiesDelightConfig.stepSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingSpeedBonusLimit;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodSpeedBonus, maxbonus);
    }

    public double increase()
    {

        foodSpeedBonus = foodSpeedBonus + step;
        return Math.min(foodSpeedBonus, maxbonus);
    }
    public void reset()
    {
        this.foodSpeedBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxSpeedBonus;
        this.step = TaotiesDelightConfig.stepSpeedBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingSpeedBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodSpeedBonus, maxbonus);
    }

    public void clone(PlayerFoodSpeedBonus bonus)
    {
        this.foodSpeedBonus = bonus.foodSpeedBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodSpeedBoost", foodSpeedBonus);
        tag.putDouble("FoodSpeedMax",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodSpeedBonus = tag.getDouble("FoodSpeedBoost");
        maxbonus = tag.getDouble("FoodSpeedMax");

    }
}
