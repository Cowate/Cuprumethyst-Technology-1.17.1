package com.cowate.cuprumethyst.Data.Server.misc;

import com.cowate.cuprumethyst.Entity.Projectile.Pebble;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class ModDamageSource extends DamageSource {
    public static final DamageSource HYPERPLASIAL = (new ModDamageSource("hyperplasial")).hyperplasial();
    private boolean isHyperplasial;

    public DamageSource hyperplasial() {
        this.isHyperplasial = true;
        return this;
    }
    public static DamageSource pebble(Pebble pebble, @Nullable Entity entity) {
        return (new IndirectEntityDamageSource("pebble", pebble, entity)).setProjectile();
    }
    public static DamageSource hefty_pebble(Pebble pebble, @Nullable Entity entity) {
        return (new IndirectEntityDamageSource("hefty_pebble", pebble, entity)).bypassInvul();
    }
    public boolean isHyperplasial(){
        return this.isHyperplasial;
    }
    public ModDamageSource(String msg) {
        super(msg);
    }

}
