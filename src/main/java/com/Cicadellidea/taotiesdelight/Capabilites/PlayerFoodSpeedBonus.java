package com.Cicadellidea.taotiesdelight.Capabilites;

import com.Cicadellidea.taotiesdelight.config.FoodSpeedBonusConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodSpeedBonus {

    private double foodSpeedBonus;

    private double maxbonus;
    private double step;

    public PlayerFoodSpeedBonus()
    {
        this.foodSpeedBonus = 0;
        this.maxbonus = FoodSpeedBonusConfig.maxSpeedBonus;
        this.step = FoodSpeedBonusConfig.stepSpeedBonus;
    }
    public double increase()
    {

        foodSpeedBonus = foodSpeedBonus + step;
        if(foodSpeedBonus > maxbonus)
        {
            foodSpeedBonus = maxbonus;
        }
        return foodSpeedBonus;
    }
    public double getFoodSpeedBonus()
    {
        return this.foodSpeedBonus;
    }

    public void setFoodSpeedBonus(double foodSpeedBonus) {
        this.foodSpeedBonus = foodSpeedBonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodSpeedBoost", foodSpeedBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodSpeedBonus = tag.getDouble("FoodSpeedBoost");
    }
}
