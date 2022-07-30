package com.cowate.cuprumethyst.Data.server.MixingTech;


import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MixingRecipeRegistry {
    private static List<IMixingRecipe> recipes = new ArrayList<IMixingRecipe>();


    public static boolean addRecipe(ItemStack input0, ItemStack input1, ItemStack output) {
        return addRecipe(new MixingRecipe(input0, input1, output));
    }

    public static boolean addRecipe(IMixingRecipe recipe) {
        return recipes.add(recipe);
    }

    public static ItemStack getOutput(ItemStack input0, ItemStack input1) {
        if (input0.isEmpty() || input1.isEmpty()) return ItemStack.EMPTY;

        for (IMixingRecipe recipe : recipes) {
            ItemStack output = recipe.getOutput(input0, input1);
            if (!output.isEmpty()) {
                return output;
            }
        }
        return ItemStack.EMPTY;
    }

    public static boolean hasOutput(ItemStack input0, ItemStack input1) {
        return !getOutput(input0, input1).isEmpty() || !getOutput(input1, input0).isEmpty();
    }

    public static boolean canMix(ItemStack input0, ItemStack input1, ItemStack output) {
        if (hasOutput(input0, input1)) {

        }
        return false;
    }

    public static void mixPotions(NonNullList<ItemStack> inputs, ItemStack ingredient, int[] inputIndexes) {
        for (int i : inputIndexes) {
            ItemStack output = getOutput(inputs.get(i), ingredient);
            if (!output.isEmpty()) {
                inputs.set(i, output);
            }
        }
    }

    public static boolean isValidOutput(ItemStack output) {
        if (output.getItem() == Items.GLASS_BOTTLE) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidInput(ItemStack input) {
        if (input.getCount() != 1) return false;
        for (IMixingRecipe recipe : recipes) {
            if (recipe.isInput(input)) {
                return true;
            }
        }
        return false;
    }

    public static List<IMixingRecipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }
}
