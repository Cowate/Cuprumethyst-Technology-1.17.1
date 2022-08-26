package com.cowate.cuprumethyst.Data.Server.recipes;

import com.cowate.cuprumethyst.Initailize.ModRecipeSerializers;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.util.JsonUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class MixingRecipe implements Recipe<Container> {
    protected final RecipeType<?> type;
    protected final ResourceLocation id;
    protected final Ingredient input0;

    protected final Ingredient input1;
    protected final ItemStack result;
    protected final int mixingTime;

    public MixingRecipe(ResourceLocation recipeId, Ingredient input0, Ingredient input1, ItemStack result) {
        this.type = ModRecipeSerializers.Types.MIXING;
        this.id = recipeId;
        this.input0 = input0;
        this.input1 = input1;
        this.result = result;
        this.mixingTime = 400;
    }

    public MixingRecipe(ResourceLocation recipeId, Ingredient input0, Ingredient input1, ItemStack result, int timeCost) {
        this.type = ModRecipeSerializers.Types.MIXING;
        this.id = recipeId;
        this.input0 = input0;
        this.input1 = input1;
        this.result = result;
        this.mixingTime = timeCost;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.input0.test(container.getItem(0)) && this.input1.test(container.getItem(1));
    }

    @Override public ItemStack assemble(Container container) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.Serializers.MIXING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<MixingRecipe> {
        @Override
        public MixingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient input0 = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input0"));
            Ingredient input1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input1"));
            ResourceLocation itemId = new ResourceLocation(JsonUtils.getStringOr("result", json, ""));

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), 1);

            return new MixingRecipe(recipeId, input0, input1, result);
        }

        @Nullable
        @Override
        public MixingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            Ingredient input0 = Ingredient.fromNetwork(buffer);
            Ingredient input1 = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new MixingRecipe(recipeId, input0, input1, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingRecipe recipe) {
            recipe.input0.toNetwork(buffer);
            recipe.input1.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }
}
