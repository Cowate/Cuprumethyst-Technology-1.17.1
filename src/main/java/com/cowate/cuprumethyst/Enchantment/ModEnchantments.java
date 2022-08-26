package com.cowate.cuprumethyst.Enchantment;

import com.cowate.cuprumethyst.Initailize.Registeries;
import com.cowate.cuprumethyst.Item.SlingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.Arrays;

public class ModEnchantments {
    private static final int COUNT = 1;
    public static final RegistryObject<Enchantment> HEFTY = Registeries.ENCHANTMENTS.register(
            "hefty", () -> new PebbleHeftyEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

    public static void register() {
        CreativeModeTab.TAB_COMBAT.setEnchantmentCategories(EnchantmentCategory.VANISHABLE, EnchantmentCategory.ARMOR, EnchantmentCategory.ARMOR_FEET, EnchantmentCategory.ARMOR_HEAD, EnchantmentCategory.ARMOR_LEGS, EnchantmentCategory.ARMOR_CHEST, EnchantmentCategory.BOW, EnchantmentCategory.WEAPON, EnchantmentCategory.WEARABLE, EnchantmentCategory.BREAKABLE, EnchantmentCategory.TRIDENT, EnchantmentCategory.CROSSBOW, ModEnchantmentCategory.SLING);
    }
}
