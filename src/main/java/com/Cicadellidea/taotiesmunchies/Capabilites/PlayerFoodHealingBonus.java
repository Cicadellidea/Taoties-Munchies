package com.Cicadellidea.taotiesmunchies.Capabilites;

import com.Cicadellidea.taotiesmunchies.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodHealingBonus {

    public double foodHealingBonus;
    public double limitBreakingStep;
    public double maxbonus;
    public double step;

    public PlayerFoodHealingBonus()
    {
        this.foodHealingBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxHealingBonus;
        this.step = TaotiesDelightConfig.stepHealingBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingHealingBonusLimit;
    }
    public void setbonus(double value)
    {
        foodHealingBonus = value;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodHealingBonus, maxbonus);
    }

    public double increase()
    {

        foodHealingBonus = foodHealingBonus + step;
        return Math.min(foodHealingBonus, maxbonus);
    }
    public void reset()
    {
        this.foodHealingBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxHealingBonus;
        this.step = TaotiesDelightConfig.stepHealingBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingHealingBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodHealingBonus, maxbonus);
    }

    public void clone(PlayerFoodHealingBonus bonus)
    {
        this.foodHealingBonus = bonus.foodHealingBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodHealingBonus", foodHealingBonus);
        tag.putDouble("maxHealingBonus",maxbonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodHealingBonus = tag.getDouble("FoodHealingBonus");
        maxbonus = tag.getDouble("maxHealingBonus");
    }
}
