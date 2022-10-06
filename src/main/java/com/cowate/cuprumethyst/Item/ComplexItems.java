package com.cowate.cuprumethyst.Item;

import com.cowate.cuprumethyst.Data.Server.misc.ModTiers;
import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.world.item.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class ComplexItems {

    public static final RegistryObject<Item> SLING = register(
            "sling",
            () -> new SlingItem(new Item.Properties().durability(80).tab(CreativeModeTab.TAB_COMBAT))
    );
    public static final RegistryObject<SwordItem> CUPRUMETHYST_SWORD = register(
            "cuprumethyst_sword",
            () -> new SwordItem(ModTiers.CUPRUMETHYST, 3, -2.4F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT))
    );
    public static final RegistryObject<ShovelItem> CUPRUMETHYST_SHOVEL = register(
            "cuprumethyst_shovel",
            () -> new ShovelItem(ModTiers.CUPRUMETHYST, 1.5F, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS))
    );
    public static final RegistryObject<PickaxeItem> CUPRUMETHYST_PICKAXE = register(
            "cuprumethyst_pickaxe",
            () -> new PickaxeItem(ModTiers.CUPRUMETHYST, 1, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS))
    );
    public static final RegistryObject<AxeItem> CUPRUMETHYST_AXE = register(
            "cuprumethyst_axe",
            () -> new AxeItem(ModTiers.CUPRUMETHYST, 6.0F, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS))
    );
    public static final RegistryObject<HoeItem> CUPRUMETHYST_HOE = register(
            "cuprumethyst_hoe",
            () -> new HoeItem(ModTiers.CUPRUMETHYST, -3, -1.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS))
    );
    public static void register() {

    }
    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return Registeries.ITEMS.register(name, supplier);
    }
}
