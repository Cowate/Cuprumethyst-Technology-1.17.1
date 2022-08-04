package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierScreen;
import com.cowate.cuprumethyst.Cuprumethyst;

import com.cowate.cuprumethyst.Data.client.ModBlockStateProvider;
import com.cowate.cuprumethyst.Data.client.ModItemModelProvider;
import com.cowate.cuprumethyst.Data.server.recipes.PotionMixing;
import com.cowate.cuprumethyst.Initailize.ModContainerTypes;
import com.cowate.cuprumethyst.Initailize.ModPotions;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.alchemy.Potion;
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
    }

}
