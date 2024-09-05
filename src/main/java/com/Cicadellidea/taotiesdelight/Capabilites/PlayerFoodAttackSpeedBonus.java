package com.Cicadellidea.taotiesdelight.Capabilites;

import net.minecraft.nbt.CompoundTag;

public class PlayerFoodAttackSpeedBonus {

    private float foodAttackSpeedBonus;
    private float maxbonus;
    private float step;

    public PlayerFoodAttackSpeedBonus()
    {
        this.foodAttackSpeedBonus = 0;
        this.maxbonus = 1;
        this.step = 0.1f;
    }
    public float increase()
    {

        foodAttackSpeedBonus = foodAttackSpeedBonus + step;
        if(foodAttackSpeedBonus > maxbonus)
        {
            foodAttackSpeedBonus = maxbonus;
        }
        return foodAttackSpeedBonus;
    }

    public float getFoodAttackSpeedBonus() {
        return foodAttackSpeedBonus;
    }

    public void setFoodAttackSpeedBonus(float foodAttackSpeedBonus) {
        this.foodAttackSpeedBonus = foodAttackSpeedBonus;
    }



    public void saveNBT(CompoundTag tag)
    {
        tag.putFloat("FoodSpeedBoost", foodAttackSpeedBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodAttackSpeedBonus = tag.getFloat("FoodSpeedBoost");
    }
}
