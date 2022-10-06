package com.cowate.cuprumethyst.Item;

import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class SimpleItems {

    public static final RegistryObject<Item> AMETHYST_DUST = registrySimpleItems(
            "amethyst_dust", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> OXIDIZED_COPPER_INGOT = registrySimpleItems(
            "oxidized_copper_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> RAW_CUPRUMETHYST_INGOT = registrySimpleItems(
            "raw_cuprumethyst_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SOUL_POWDER = registrySimpleItems(
            "soul_powder", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SOUL_ROD = registrySimpleItems(
            "soul_rod", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> CUPRUMETHYST_INGOT = registrySimpleItems(
            "cuprumethyst_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> PEBBLE_STONE = registrySimpleItems(
            "pebble_stone", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> PEBBLE_BLACKSTONE = registrySimpleItems(
            "pebble_blackstone", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static void register() {
    }

    private static <T extends Item> RegistryObject<Item> registrySimpleItems(String name, Supplier<T> supplier) {
        return Registeries.ITEMS.register(name, supplier);
    }
}
