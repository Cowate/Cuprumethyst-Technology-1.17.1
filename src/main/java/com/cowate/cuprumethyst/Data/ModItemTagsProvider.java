package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Data.server.misc.ModTags;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, Cuprumethyst.MOD_ID, existingFileHelper);
    }


    protected void addTags(){
        copy(ModTags.Blocks.RAW_CUPRUMETHYST_BLOCK, ModTags.Items.RAW_CUPRUMETHYST_BLOCK);
        copy(ModTags.Blocks.STORAGE_BLOCKS_CUPRUMETHYST, ModTags.Items.STORAGE_BLOCKS_CUPRUMETHYST);
        copy(ModTags.Blocks.STORAGE_BLOCKS_OXIDIZED_COPPER, ModTags.Items.STORAGE_BLOCKS_OXIDIZED_COPPER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);


        tag(ModTags.Items.DUSTS_AMETHYST).add(SimpleItems.AMETHYST_DUST.get());
        tag(ModTags.Items.INGOT_OXIDIZED_COPPER).add(SimpleItems.OXIDIZED_COPPER_INGOT.get());
        tag(Tags.Items.INGOTS)
                .addTag(ModTags.Items.INGOT_OXIDIZED_COPPER)
                .add(SimpleItems.RAW_CUPRUMETHYST_INGOT.get(), SimpleItems.CUPRUMETHYST_INGOT.get());
        tag(ModTags.Items.PEBBLES_STONE).add(SimpleItems.PEBBLE_STONE.get());
        tag(ModTags.Items.PEBBLES_BLACKSTONE).add(SimpleItems.PEBBLE_BLACKSTONE.get());
        tag(ModTags.Items.PEBBLES).addTag(ModTags.Items.PEBBLES_STONE).addTag(ModTags.Items.PEBBLES_BLACKSTONE);

    }
}
