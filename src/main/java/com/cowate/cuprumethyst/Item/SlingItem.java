package com.cowate.cuprumethyst.Item;

import com.cowate.cuprumethyst.Data.Server.misc.ModTags;
import com.cowate.cuprumethyst.Enchantment.ModEnchantments;
import com.cowate.cuprumethyst.Entity.Projectile.Pebble;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class SlingItem extends ProjectileWeaponItem implements Vanishable {
    public static final int DEFAULT_RANGE = 8;
    public static final Predicate<ItemStack> PEBBLES_ONLY = (item) -> item.is(ModTags.Items.PEBBLES);
    public SlingItem(Item.Properties properties) {
        super(properties);
    }
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return PEBBLES_ONLY;
    }
    @Override
    public int getDefaultProjectileRange() {
        return DEFAULT_RANGE;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        //play sound TODO

        ItemStack projectile = player.getProjectile(player.getItemInHand(hand));
        boolean hasProjectile = !projectile.isEmpty();

        if (!hasProjectile) {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
        if (!level.isClientSide) {
            player.getCooldowns().addCooldown(this, 20);
            Pebble pebble = new Pebble(level, player);
            pebble.setPebbleType(Pebble.testPebbleType(projectile));
            pebble.setHeftyEffect(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.HEFTY.get(), player.getItemInHand(hand)) > 0);
            pebble.setOwner(player);
            pebble.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(pebble);
            projectile.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

}
