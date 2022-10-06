package com.cowate.cuprumethyst.Utils.Data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerStatueMemoryProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerStatueMemory> PLAYER_STATUE_MEMORY = CapabilityManager.get(new CapabilityToken<>(){});

    private PlayerStatueMemory statueMemory = null;
    private final LazyOptional<PlayerStatueMemory> opt = LazyOptional.of(this::createStatueMemory);

    private PlayerStatueMemory createStatueMemory() {
        if (statueMemory == null) {
            statueMemory = new PlayerStatueMemory();
        }
        return statueMemory;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == PLAYER_STATUE_MEMORY) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createStatueMemory().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createStatueMemory().loadNBTData(nbt);
    }
}
