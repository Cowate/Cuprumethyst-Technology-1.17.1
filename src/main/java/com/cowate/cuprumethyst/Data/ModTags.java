package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static final class Blocks{

        public static final Tags.IOptionalNamedTag<Block> RAW_CUPRUMETHYST_BLOCK = createTag("blocks/raw_cuprumethyst_block");
        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_OXIDIZED_COPPER = createForgeTag("storage_blocks/oxidized_copper");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Cuprumethyst.MOD_ID, name));
        }
        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static final class Items{

        public static final Tags.IOptionalNamedTag<Item> DUSTS_AMETHYST = createForgeTag("dusts/amethyst_dust");
        public static final Tags.IOptionalNamedTag<Item> INGOT_OXIDIZED_COPPER = createForgeTag("ingots/oxidized_copper");
        public static final Tags.IOptionalNamedTag<Item> RAW_CUPRUMETHYST_BLOCK = createTag("blocks/raw_cuprumethyst_block");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_OXIDIZED_BLOCK = createForgeTag("storage_blocks/oxidized_copper");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Cuprumethyst.MOD_ID, name));
        }
        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }

    }

}
