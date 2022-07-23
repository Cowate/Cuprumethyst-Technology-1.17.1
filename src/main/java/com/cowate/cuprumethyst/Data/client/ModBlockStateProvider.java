package com.cowate.cuprumethyst.Data.client;

import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Cuprumethyst.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get());

    }
}
