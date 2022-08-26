package com.cowate.cuprumethyst.Enchantment;

import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Item.SlingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;

@Mod(value = Cuprumethyst.MOD_ID)
public class PebbleHeftyEnchantment extends Enchantment {
    protected PebbleHeftyEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, ModEnchantmentCategory.SLING, slots);
    }



}
