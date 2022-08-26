package com.cowate.cuprumethyst.Enchantment;

import com.cowate.cuprumethyst.Item.SlingItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantmentCategory {
    public static EnchantmentCategory SLING = EnchantmentCategory.create("SLING", item -> item instanceof SlingItem);
}
