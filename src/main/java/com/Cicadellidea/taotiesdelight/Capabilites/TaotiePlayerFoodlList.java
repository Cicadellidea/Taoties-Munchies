package com.Cicadellidea.taotiesdelight.Capabilites;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TaotiePlayerFoodlList {
    private Set<String> foodSet;

    public TaotiePlayerFoodlList()
    {
        this.foodSet = new HashSet<>();
    }

    public Set<String> getFoodSet() {
        return foodSet;
    }

    public void setFoodSet(Set<String> foodSet) {
        this.foodSet = foodSet;
    }

    public boolean add(Item item)
    {
        String id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        boolean bool = !(this.foodSet.contains(id));
        if(bool)
        {
            this.foodSet.add(id);
        }
        return  bool;
    }
    public void readNBT(CompoundTag tag)
    {

        this.foodSet = tag.getList("TaotieFoodList",0).stream().map(name->name.getAsString()).collect(Collectors.toSet());
    }

    public Tag writeNBT(CompoundTag tag)
    {
        var list = new ListTag();
        this.foodSet.stream().map(StringTag::valueOf).forEach(list::add);
        tag.put("TaotieFoodList",list);
        return tag;
    }

}
