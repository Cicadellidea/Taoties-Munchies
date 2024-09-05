package com.Cicadellidea.taotiesdelight.Capabilites;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArrowFoodAcceleratedProvider implements ICapabilityProvider, INBTSerializable {

    private ArrowFoodAccelerated arrowFoodAccelerated;

    public ArrowFoodAcceleratedProvider(){
        this.arrowFoodAccelerated = new ArrowFoodAccelerated();
    }

    public static final Capability<ArrowFoodAccelerated> ARROW_FOOD_ACCELERATED_CAPABILITY = CapabilityManager.get(new CapabilityToken<ArrowFoodAccelerated>() {});

    private final LazyOptional<ArrowFoodAccelerated> lazyOptional = LazyOptional.of(()->this.arrowFoodAccelerated);



    @Override
    public Tag serializeNBT() {
        var tag = new CompoundTag();
        arrowFoodAccelerated.saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(Tag tag) {
        arrowFoodAccelerated.readNBTData((CompoundTag) tag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
        if(cap == ARROW_FOOD_ACCELERATED_CAPABILITY)
        {
            return this.lazyOptional.cast();
        }
        else
        {
            return this.lazyOptional.empty();
        }

    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ARROW_FOOD_ACCELERATED_CAPABILITY)
        {
            return this.lazyOptional.cast();
        }
        else
        {
            return this.lazyOptional.empty();
        }
    }
}
