package com.Cicadellidea.taotiesdelight.Capabilites;

import com.Cicadellidea.taotiesdelight.config.TaotiesDelightConfig;
import net.minecraft.nbt.CompoundTag;

public class PlayerFoodResistanceBonus {

    private double foodResistanceBonus;
    private double limitBreakingStep;
    private double maxbonus;
    private double step;

    public PlayerFoodResistanceBonus()
    {
        this.foodResistanceBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxResistanceBonus;
        this.step = TaotiesDelightConfig.stepResistanceBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingResistanceBonusLimit;
    }

    public double limintBreaking()
    {
        this.maxbonus = this.maxbonus + this.limitBreakingStep;
        return Math.min(foodResistanceBonus, maxbonus);
    }

    public double increase()
    {

        foodResistanceBonus = foodResistanceBonus + step;
        return Math.min(foodResistanceBonus, maxbonus);
    }
    public void reset()
    {
        this.foodResistanceBonus = 0;
        this.maxbonus = TaotiesDelightConfig.maxResistanceBonus;
        this.step = TaotiesDelightConfig.stepResistanceBonus;
        this.limitBreakingStep = TaotiesDelightConfig.breakingResistanceBonusLimit;
    }

    public double getActual()
    {
        return Math.min(foodResistanceBonus, maxbonus);
    }

    public void clone(PlayerFoodResistanceBonus bonus)
    {
        this.foodResistanceBonus = bonus.foodResistanceBonus;
        this.maxbonus = bonus.maxbonus;
    }

    public void saveNBT(CompoundTag tag)
    {
        tag.putDouble("FoodResistanceBonus", foodResistanceBonus);
    }

    public void readNBT(CompoundTag tag)
    {
        foodResistanceBonus = tag.getDouble("FoodResistanceBonus");
    }
}

