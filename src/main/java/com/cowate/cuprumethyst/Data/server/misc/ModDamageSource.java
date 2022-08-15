package com.cowate.cuprumethyst.Data.server.misc;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource extends DamageSource {
    public static final DamageSource HYPERPLASIAL = (new ModDamageSource("hyperplasial")).hyperplasial();

    private boolean isHyperplasial;

    public DamageSource hyperplasial() {
        this.isHyperplasial = true;
        return this;
    }

    public boolean isHyperplasial(){
        return this.isHyperplasial;
    }
    public ModDamageSource(String msg) {
        super(msg);
    }

}
