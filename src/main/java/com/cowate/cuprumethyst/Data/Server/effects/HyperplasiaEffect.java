package com.cowate.cuprumethyst.Data.Server.effects;

import com.cowate.cuprumethyst.Data.Server.misc.ModDamageSource;
import com.cowate.cuprumethyst.Initailize.ModMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class HyperplasiaEffect extends MobEffect {
    private boolean activeOnEntity;
    public HyperplasiaEffect(MobEffectCategory category,  int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        boolean flag = pLivingEntity.hasEffect(MobEffects.WITHER);
        if (flag) {
            pLivingEntity.removeEffect(MobEffects.WITHER);
        }
        if (pLivingEntity.getEffect(ModMobEffects.HIPERPLASIAL.get()).getAmplifier() >= 3) {
            pLivingEntity.hurt(ModDamageSource.HYPERPLASIAL, Float.MAX_VALUE);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
