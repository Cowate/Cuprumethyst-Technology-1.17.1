package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Block.SimpleBlocks;

import com.cowate.cuprumethyst.Item.SimpleItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    private static final ImmutableList<ItemLike> CUPRUMETHYST_SMELTABLES = ImmutableList.of(SimpleItems.RAW_CUPRUMETHYST_INGOT.get());
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //Shapeless
        ShapelessRecipeBuilder.shapeless(SimpleItems.AMETHYST_DUST.get(), 2)
                .requires(Items.AMETHYST_SHARD)
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(SimpleItems.OXIDIZED_COPPER_INGOT.get(), 9)
                .requires(Items.OXIDIZED_COPPER)
                .unlockedBy("has_oxidized_copper", has(Items.OXIDIZED_COPPER))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(SimpleItems.CUPRUMETHYST_INGOT.get(), 9)
                .requires(SimpleBlocks.CUPRUMETHYST_BLOCK.get())
                .unlockedBy("has_cuprumethyst_block", has(SimpleBlocks.CUPRUMETHYST_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(SimpleItems.RAW_CUPRUMETHYST_INGOT.get(), 9)
                .requires(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get())
                .unlockedBy("has_raw_cuprumethyst_block", has(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get()))
                .save(consumer, "raw_cuprumethyst_ingot_from_block");
        ShapelessRecipeBuilder.shapeless(SimpleItems.RAW_CUPRUMETHYST_INGOT.get(), 1)
                .requires(SimpleItems.AMETHYST_DUST.get())
                .requires(SimpleItems.OXIDIZED_COPPER_INGOT.get())
                .unlockedBy("has_oxidized_copper_ingot", has(SimpleItems.OXIDIZED_COPPER_INGOT.get()))
                .save(consumer);
        //Shaped
        ShapedRecipeBuilder.shaped(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get())
                .define('#', Items.OXIDIZED_COPPER)
                .define('X', SimpleItems.AMETHYST_DUST.get())
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .unlockedBy("has_oxidized_copper", has(Items.OXIDIZED_COPPER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(SimpleBlocks.CUPRUMETHYST_BLOCK.get())
                .define('#', SimpleItems.CUPRUMETHYST_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_cuprumethyst_ingot", has(SimpleItems.CUPRUMETHYST_INGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get())
                .define('#', SimpleItems.RAW_CUPRUMETHYST_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_raw_cuprumethyst_ingot", has(SimpleItems.RAW_CUPRUMETHYST_INGOT.get()))
                .save(consumer, "raw_cuprumethyst_block_from_ingot");

        //Cooking
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(SimpleItems.RAW_CUPRUMETHYST_INGOT.get()), SimpleItems.CUPRUMETHYST_INGOT.get(), 1.0F, 200)
                .unlockedBy("has_raw_cuprumethyst", has(SimpleItems.RAW_CUPRUMETHYST_INGOT.get())).save(consumer, "cuprumethyst" + "_from_smelting" + "raw_cuprumethyst");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(SimpleItems.RAW_CUPRUMETHYST_INGOT.get()), SimpleItems.CUPRUMETHYST_INGOT.get(), 1.0F, 100)
                .unlockedBy("has_raw_cuprumethyst", has(SimpleItems.RAW_CUPRUMETHYST_INGOT.get())).save(consumer, "cuprumethyst" + "_from_blasting" + "raw_cuprumethyst");

    }
}
