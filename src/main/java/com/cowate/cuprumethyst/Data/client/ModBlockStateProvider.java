package com.cowate.cuprumethyst.Data.client;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierBlock;
import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final String ModId = Cuprumethyst.MOD_ID;
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Cuprumethyst.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get());
        simpleBlock(SimpleBlocks.CUPRUMETHYST_BLOCK.get());
        getMultipartBuilder(ComplexBlocks.SOUL_MIXIER.get())
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier"))).addModel().end()
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier_bottle0"))).addModel()
                    .condition(SoulMixierBlock.HAS_BOTTLE[0], true).end()
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier_bottle1"))).addModel()
                    .condition(SoulMixierBlock.HAS_BOTTLE[1], true).end()
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier_bottle2"))).addModel()
                    .condition(SoulMixierBlock.HAS_BOTTLE[2], true).end()
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier_empty0"))).addModel()
                    .condition(SoulMixierBlock.HAS_BOTTLE[0], false).end()
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier_empty1"))).addModel()
                    .condition(SoulMixierBlock.HAS_BOTTLE[1], false).end()
                .part().modelFile(models().getExistingFile(new ResourceLocation(ModId, "block/soul_mixier_empty2"))).addModel()
                    .condition(SoulMixierBlock.HAS_BOTTLE[2], false).end();
    }


}
