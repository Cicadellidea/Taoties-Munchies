package com.Cicadellidea.taotiesdelight.Capabilites;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaotiePlayerFoodlList {
    private Set<String> foodSet;

    public TaotiePlayerFoodlList()
    {
        this.foodSet = new HashSet<>();
    }



    public boolean add(Item item)
    {
        boolean bool = (this.foodSet.contains(item));
        if(!bool)
        {
            this.foodSet.add(item.get);
        }
        return  bool;
    }
    public void readNBT(CompoundTag tag)
    {
        this.foodSet = tag.getList("TaotieFoodList",0).stream().map(name->name.getAsString()).collect(Collectors.toSet());
    }

}
