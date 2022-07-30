package com.cowate.cuprumethyst.Data.server.MixingTech;

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

    private static final List<PotionMixing.Mix<Potion>> POTIONS_MIXES = Lists.newArrayList();


    protected static boolean isPotionMixable(ItemStack itemStack) {

        return true;
    }

    public static ItemStack mix(ItemStack input0, ItemStack input1) {
        if (!input0.isEmpty() && !input1.isEmpty()) {
            int i = 0 ;
            for (int j = POTIONS_MIXES.size(); i < j; ++i) {
                PotionMixing.Mix<Potion> mix = POTIONS_MIXES.get(i);
                if (mix.input0.get() == PotionUtils.getPotion(input0) && mix.input1.get() == PotionUtils.getPotion(input1)
                        || mix.input1.get() == PotionUtils.getPotion(input0) && mix.input0.get() == PotionUtils.getPotion(input1)) {
                    // to do
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static void register() {
        addMix(Potions.WATER, Potions.WATER, ModPotions.TESTING_LIQUID.get());

    }

    private static void addMix(Potion in0, Potion in1, Potion out) {
        POTIONS_MIXES.add(new PotionMixing.Mix<>(in0, in1, out));
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
