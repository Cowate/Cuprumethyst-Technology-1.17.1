package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Data.server.effects.HyperplasiaEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModMobEffects {
    public static final RegistryObject<MobEffect> HIPERPLASIAL = Registeries.MOB_EFFECT.register("hiperplasial",
            () -> new HyperplasiaEffect(MobEffectCategory.BENEFICIAL, 16054272));

    public static void register() {

    }

}
