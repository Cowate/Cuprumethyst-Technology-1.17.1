package com.cowate.cuprumethyst.Item;

import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class ComplexItems {

    public static final RegistryObject<Item> SLING = register(
            "sling",
            () -> new SlingItem(new Item.Properties().durability(152).tab(CreativeModeTab.TAB_COMBAT))
    );


    public static void register() {

    }
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return Registeries.ITEMS.register(name, supplier);
    }
}
