package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Data.server.recipes.MixingRecipe;
import com.cowate.cuprumethyst.Data.server.recipes.MixingRecipeBuilder;
import com.cowate.cuprumethyst.Data.server.recipes.PotionMixing;
import com.cowate.cuprumethyst.Initailize.ModPotions;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
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
        ShapelessRecipeBuilder.shapeless(SimpleItems.RAW_CUPRUMETHYST_INGOT.get(), 1)
                .requires(SimpleItems.AMETHYST_DUST.get())
                .requires(SimpleItems.OXIDIZED_COPPER_INGOT.get())
                .unlockedBy("has_oxidized_copper", has(SimpleItems.OXIDIZED_COPPER_INGOT.get()))
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


    }
}
