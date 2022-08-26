package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierScreen;
import com.cowate.cuprumethyst.Cuprumethyst;

import com.cowate.cuprumethyst.Data.Client.ModBlockModelProvider;
import com.cowate.cuprumethyst.Data.Client.ModBlockStateProvider;
import com.cowate.cuprumethyst.Data.Client.ModItemModelProvider;
import com.cowate.cuprumethyst.Data.Server.recipes.PlantRecipes;
import com.cowate.cuprumethyst.Initailize.ModContainerTypes;
import com.cowate.cuprumethyst.Initailize.ModPotions;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Cuprumethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new ModBlockModelProvider(gen, existingFileHelper));
        gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));

        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(blockTagsProvider);
        gen.addProvider(new ModItemTagsProvider(gen, blockTagsProvider, existingFileHelper));

        gen.addProvider(new ModLootTableProvider(gen));
        gen.addProvider(new ModRecipeProvider(gen));

    }
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {

        MenuScreens.register(ModContainerTypes.SOUL_MIXIER.get(), SoulMixierScreen::new);
        ItemBlockRenderTypes.setRenderLayer(ComplexBlocks.SOUL_MIXIER.get(), RenderType.cutout());

        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new PlantRecipes(Potions.WATER, Items.CRIMSON_ROOTS, ModPotions.CRIMILIDIN.get()));
            BrewingRecipeRegistry.addRecipe(new PlantRecipes(Potions.WATER, Items.WARPED_ROOTS, ModPotions.WARPILIDIN.get()));
        });
    }

}
