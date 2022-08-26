package com.cowate.cuprumethyst.Data.Server.recipes;

import com.cowate.cuprumethyst.Initailize.ModPotions;
import com.google.common.collect.Lists;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IRegistryDelegate;

import java.util.List;

public class PotionMixing {
    public static final int MIXING_TIME_SECONDS = 40;

    private static final List<PotionMixing.Mix<Potion>> POTION_MIXES = Lists.newArrayList();

    private static Potion toPotion(ItemStack itemStack) {
        return PotionUtils.getPotion(itemStack);
    }

    public static boolean isPotionMixable(ItemStack itemStack) {
        return isMixalbePotion(toPotion(itemStack));
    }

    public static boolean isMixalbePotion(Potion potion) {
        int i = 0;
        for (int j = POTION_MIXES.size(); i < j; ++i) {
            if ((POTION_MIXES.get(i)).input1.get() == potion
                || (POTION_MIXES.get(i).input0.get() == potion)) {
                return true;
            }
        }
        return false;
    }

    public static boolean onRecipes(ItemStack in0, ItemStack in1) {
        Potion input0 = toPotion(in0);
        Potion input1 = toPotion(in1);
        int i = 0;
        for (int j = POTION_MIXES.size(); i < j; ++i) {
            if ((POTION_MIXES.get(i)).input0.get() == input0 && (POTION_MIXES.get(i)).input1.get() == input1
                || (POTION_MIXES.get(i)).input1.get() == input0 && (POTION_MIXES.get(i)).input0.get() == input1) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getOutput(ItemStack in0, ItemStack in1) {
        Potion input0 = toPotion(in0);
        Potion input1 = toPotion(in1);
        ItemStack result = in0.copy();
        int i = 0;
        for (int j = POTION_MIXES.size(); i < j; ++i) {
            if ((POTION_MIXES.get(i)).input0.get() == input0 && (POTION_MIXES.get(i)).input1.get() == input1
                    || (POTION_MIXES.get(i)).input1.get() == input0 && (POTION_MIXES.get(i)).input0.get() == input1) {
                Potion potion = POTION_MIXES.get(i).output.get();
                result = PotionUtils.setPotion(result, POTION_MIXES.get(i).output.get());
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    static {
        addMix(ModPotions.CRIMILIDIN.get(), ModPotions.WARPILIDIN.get(), ModPotions.HYPERPLASIAL.get());
    }

    private static void addMix(Potion in0, Potion in1, Potion out) {
        POTION_MIXES.add(new PotionMixing.Mix<>(in0, in1, out));
    }

    public static class Mix<T extends ForgeRegistryEntry<T>> {
        public final IRegistryDelegate<T> input0;
        public final IRegistryDelegate<T> input1;
        public final IRegistryDelegate<T> output;

        public Mix(T input0, T input1, T output) {
            this.input0 = input0.delegate;
            this.input1 = input1.delegate;
            this.output = output.delegate;
        }
    }

}
