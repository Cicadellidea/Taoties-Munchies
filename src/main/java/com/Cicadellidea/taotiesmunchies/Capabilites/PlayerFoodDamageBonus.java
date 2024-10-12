package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodDamageBonus {

    public double foodDamageBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodDamageBonus()
    {
        this.foodDamageBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxDamageBonus;
        this.step = TaotiesDelightConfig.stepDamageBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingDamageBonusLimit;
    }
    public void setbonus(double value)
    {
        foodDamageBonus = value;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodDamageBonus, maxbonus);
    }

    public double increase()
    {

        foodDamageBonus = foodDamageBonus + step;
        return Math.min(foodDamageBonus, maxbonus);
    }
    public void reset()
    {
        this.foodDamageBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxDamageBonus;
        this.step = TaotiesDelightConfig.stepDamageBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingDamageBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodDamageBonus, maxbonus);
    }

    public void clone(PlayerFoodDamageBonus bonus)
    {
        this.foodDamageBonus = bonus.foodDamageBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodDamageBonus", foodDamageBonus);
        tag.putDouble("maxDamageBonus",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodDamageBonus = tag.getDouble("FoodDamageBonus");
        maxbonus = tag.getDouble("maxDamageBonus");
    }
}
