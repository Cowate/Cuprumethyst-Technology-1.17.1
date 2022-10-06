package com.cowate.cuprumethyst.Data.Server.misc;

import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier CUPRUMETHYST = new ForgeTier(3, 450, 6.0F, 2.5F, 20,
        BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(SimpleItems.CUPRUMETHYST_INGOT.get()));
}
