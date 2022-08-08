package com.cowate.cuprumethyst.Block;


import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierBlock;
import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class ComplexBlocks{

    public static final RegistryObject<SoulMixierBlock> SOUL_MIXIER = register(
            "soul_mixier",
            () -> new SoulMixierBlock(BlockBehaviour.Properties
                    .copy(Blocks.BREWING_STAND)),
            CreativeModeTab.TAB_BREWING
    );

    public static void register(){

    }

    private static <T extends Block>RegistryObject<T> registerComplexItem(String name, Supplier<T> block){
        return Registeries.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, CreativeModeTab tab){

        RegistryObject<T> ret = registerComplexItem(name, block);
        Registeries.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(tab)));

        return ret;
    }

}
