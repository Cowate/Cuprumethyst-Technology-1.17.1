package com.cowate.cuprumethyst.Item;

import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class SimpleItems {

    public static final RegistryObject<Item> AMETHYST_DUST = registrySimpleItems(
            "amethyst_dust", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> OXIDIZED_COPPER_INGOT = registrySimpleItems(
            "oxidized_copper_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> RAW_CUPRUMETHYST_INGOT = registrySimpleItems(
            "raw_cuprumethyst_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static void register() {
    }

    private static <T extends Item> RegistryObject<Item> registrySimpleItems(String name, Supplier<T> supplier) {
        return Registeries.ITEMS.register(name, supplier);
    }
}
