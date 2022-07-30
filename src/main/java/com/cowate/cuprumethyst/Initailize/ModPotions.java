package com.cowate.cuprumethyst.Initailize;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class ModPotions {
    public static final RegistryObject<Potion> TESTING_LIQUID = registerPotion("testing_liquid", () -> new Potion());

    public static void register() {

    }

    private static <T extends Potion> RegistryObject<Potion> registerPotion(String name, Supplier<T> supplier) {
        return Registeries.POTIONS.register(name, supplier);
    }

}
