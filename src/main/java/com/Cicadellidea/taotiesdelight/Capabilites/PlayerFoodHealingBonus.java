package com.Cicadellidea.taotiesdelight.Capabilites;

import com.Cicadellidea.taotiesdelight.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodHealingBonus {

    private double foodHealingBonus;
    private double limitBreakingStep;
    private double maxbonus;
    private double step;

    public PlayerFoodHealingBonus()
    {
        this.foodHealingBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxHealingBonus;
        this.step = TaotiesDelightConfig.stepHealingBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingHealingBonusLimit;
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
    }

    public void readNBT(CompoundTag tag)
    {
        foodHealingBonus = tag.getDouble("FoodHealingBonus");
    }
}
