package com.cowate.cuprumethyst.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Vanishable;

import java.util.function.Predicate;

public class SlingItem extends ProjectileWeaponItem implements Vanishable {

    public SlingItem(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return null;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }
}
