package com.Cicadellidea.taotiesmunchies.Capabilites;

import net.minecraft.nbt.CompoundTag;

public class ArrowFoodAccelerated
{

    private boolean foodAccelerated;

    public ArrowFoodAccelerated(){
        this.foodAccelerated = false;
    }

    public boolean getFoodAccelerated()
    {
        return foodAccelerated;
    }

    public void setFoodAccelerated(boolean acc)
    {
        this.foodAccelerated = acc;
    }

    public void saveNBTData(CompoundTag tag)
    {
        tag.putBoolean("FoodAccelerated", foodAccelerated);
    }

    public void readNBTData(CompoundTag tag)
    {
        foodAccelerated = tag.getBoolean("FoodAccelerated");
    }
}
