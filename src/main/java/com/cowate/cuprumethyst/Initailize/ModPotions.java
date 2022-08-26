package com.cowate.cuprumethyst.Initailize;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModPotions {
    public static final RegistryObject<Potion> CRIMILIDIN = Registeries.POTIONS.register("crimilidin",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 800, 3)));
    public static final RegistryObject<Potion> WARPILIDIN = Registeries.POTIONS.register("warpilidin",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 800, 3)));
    public static final RegistryObject<Potion> HYPERPLASIAL = Registeries.POTIONS.register("hyperplasial",
            () -> new Potion(new MobEffectInstance(ModMobEffects.HIPERPLASIAL.get(), 2000, 0)));
    public static void register() {

    }


}
