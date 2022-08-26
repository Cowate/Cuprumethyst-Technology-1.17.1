package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Data.Server.misc.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator p_126511_, ExistingFileHelper existingFileHelper) {
        super(p_126511_, Cuprumethyst.MOD_ID, existingFileHelper);
    }

    protected void addTags(){
        // do not empty the tag set while tag in someone
        tag(ModTags.Blocks.RAW_CUPRUMETHYST_BLOCK).add(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get(), SimpleBlocks.CUPRUMETHYST_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get(), SimpleBlocks.CUPRUMETHYST_BLOCK.get()).add(ComplexBlocks.SOUL_MIXIER.get());

        tag(ModTags.Blocks.STORAGE_BLOCKS_OXIDIZED_COPPER).add(Blocks.OXIDIZED_COPPER);
        tag(ModTags.Blocks.STORAGE_BLOCKS_CUPRUMETHYST).add(SimpleBlocks.CUPRUMETHYST_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_OXIDIZED_COPPER).addTag(ModTags.Blocks.STORAGE_BLOCKS_CUPRUMETHYST);


    }
}
