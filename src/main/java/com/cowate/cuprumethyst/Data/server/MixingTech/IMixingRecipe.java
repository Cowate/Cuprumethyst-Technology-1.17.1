package com.cowate.cuprumethyst.Data.server.MixingTech;

import net.minecraft.world.item.ItemStack;

public interface IMixingRecipe {
    boolean isInput(ItemStack input);

    boolean isOutput(ItemStack output);

    ItemStack getOutput(ItemStack input0, ItemStack input1);
}
