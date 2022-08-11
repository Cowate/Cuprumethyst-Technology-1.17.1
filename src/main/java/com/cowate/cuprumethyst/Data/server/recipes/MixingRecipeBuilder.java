package com.cowate.cuprumethyst.Data.server.recipes;

import com.cowate.cuprumethyst.Initailize.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MixingRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final Ingredient input0;
    private final Ingredient input1;
    private final int mixingTime;
    private String group;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final RecipeSerializer<?> serializer;


    public MixingRecipeBuilder(ItemLike result, Ingredient input0, Ingredient input1, int mixingTime) {
        this.result = result.asItem();
        this.input0 = input0;
        this.input1 = input1;
        this.mixingTime = mixingTime;
        this.serializer= ModRecipeSerializers.Serializers.MIXING.get();
    }

    public static MixingRecipeBuilder addMixRecipe(ItemLike result, Ingredient in0, Ingredient in1, int time) {
        return new MixingRecipeBuilder(result, in0, in1, time);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance instance) {
        this.advancement.addCriterion(name, instance);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation recipeId) {
        this.ensureValid(recipeId);
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        consumer.accept(new MixingRecipeBuilder.Result(
                recipeId,
                this.group == null ? "" : this.group,
                this.input0,
                this.input1,
                this.result,
                this.mixingTime,
                this.advancement,
                new ResourceLocation(recipeId.getNamespace(),"recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + recipeId.getPath()),
                this.serializer));
    }

    private void ensureValid(ResourceLocation recipeId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtain recipe " + recipeId);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient input0;
        private final Ingredient input1;
        private final int mixingTime;
        private final String group;
        private final Advancement.Builder advancement;
        private final RecipeSerializer<?> serializer;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, String group, Ingredient input0, Ingredient input1, Item result, int mixingTime, Advancement.Builder advancement, ResourceLocation advancementId, RecipeSerializer<?> serializer) {
            this.id = id;
            this.group = group;
            this.input0 = input0;
            this.input1 = input1;
            this.result = result;
            this.mixingTime = mixingTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }
            json.add("input0", this.input0.toJson());
            json.add("input1", this.input1.toJson());
            json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
            json.addProperty("mixingtime", this.mixingTime);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
