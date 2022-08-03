package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Data.server.recipes.MixingRecipe;
import com.cowate.cuprumethyst.Data.server.recipes.PotionMixing;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModRecipeSerializers {
    public static class Types {
        public static final RecipeType<MixingRecipe> MIXING = RecipeType.register(
                Cuprumethyst.MOD_ID + "mixing");
    }

    public static class Serializers {
        public static final RegistryObject<RecipeSerializer<?>> MIXING = Registeries.RECIPE_SERIALIZERS.register(
                "mixing", MixingRecipe.Serializer::new);

    }
    public static void register() {
    }
}
