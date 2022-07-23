package com.cowate.cuprumethyst.Block;

import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;


public class SimpleBlocks {


    // add Mineable Tag to set harvest level
    // add Need Tool Tag to set harvest tool
    public static final RegistryObject<Block> RAW_CUPRUMETHYST_BLOCK = register(
            "raw_cuprumethyst_block", () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3,10)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)));

    public static void register() {

    }

    private static <T extends Block> RegistryObject<T> registerSimpleItem(String name, Supplier<T> block){
        return Registeries.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block){

        RegistryObject<T> ret = registerSimpleItem(name, block);
        Registeries.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

        return ret;
    }

}
