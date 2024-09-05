package com.Cicadellidea.taotiesdelight.Capabilites;

import net.minecraft.nbt.CompoundTag;

public class PlayerFoodDamageBonus {

    private float foodDamageBonus;
    private float maxbonus;
    private float step;

    public PlayerFoodDamageBonus()
    {
        this.foodDamageBonus = 0;
        this.maxbonus = 1;
        this.step =0.1f;
    }
    public float increase()
    {

        foodDamageBonus = foodDamageBonus + step;
        if(foodDamageBonus > maxbonus)
        {
            foodDamageBonus = maxbonus;
        }

        return foodDamageBonus;
    }
    public float getFoodDamageBonus() {
        return foodDamageBonus;
    }

    public void setFoodDamageBonus(float foodDamageBonus) {
        this.foodDamageBonus = foodDamageBonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putFloat("FoodSpeedBoost", foodDamageBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodDamageBonus = tag.getFloat("FoodSpeedBoost");
    }
}
