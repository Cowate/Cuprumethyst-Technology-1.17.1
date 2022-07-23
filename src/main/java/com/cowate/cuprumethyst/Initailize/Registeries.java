package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registeries {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cuprumethyst.MOD_ID);

    public static void register() {
        IEventBus modEventbus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventbus);
        BLOCKS.register(modEventbus);

        SimpleItems.register();
        SimpleBlocks.register();
    }

}
