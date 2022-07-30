package com.cowate.cuprumethyst.Data.server.MixingTech;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import javax.annotation.Nonnull;

public class MixingRecipe implements IMixingRecipe {
    @Nonnull private final ItemStack input0;
    @Nonnull private final ItemStack input1;
    @Nonnull private final ItemStack output;

    public MixingRecipe(ItemStack input0, ItemStack input1, ItemStack output) {
        this.input0 = input0;
        this.input1 = input1;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input) {
        return false;
    }

    @Override
    public boolean isOutput(ItemStack output) {
        return Items.GLASS_BOTTLE == output.getItem();
    }

    @Override
    public ItemStack getOutput(ItemStack input0, ItemStack input1) {
        return isInput(input0) ? ItemStack.EMPTY : ItemStack.EMPTY;
    }


}
