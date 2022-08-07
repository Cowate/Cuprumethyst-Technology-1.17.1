package com.cowate.cuprumethyst.Initailize;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModPotions {
    public static final RegistryObject<Potion> TESTING_LIQUID = Registeries.POTIONS.register("testing_liquid",
            () -> new Potion(new MobEffectInstance(MobEffects.HUNGER, 60, 1)
    ));

    public static void register() {

    }


}
