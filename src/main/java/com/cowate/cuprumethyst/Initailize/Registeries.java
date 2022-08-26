package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Item.ComplexItems;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registeries {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Cuprumethyst.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cuprumethyst.MOD_ID);

    public static void register() {
        IEventBus modEventbus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventbus);
        MOB_EFFECT.register(modEventbus);
        BLOCKS.register(modEventbus);
        POTIONS.register(modEventbus);
        ENTITIES.register(modEventbus);
        CONTAINERS.register(modEventbus);
        BLOCK_ENTITIES.register(modEventbus);
        RECIPE_SERIALIZERS.register(modEventbus);

        SimpleItems.register();
        ComplexItems.register();
        SimpleBlocks.register();
        ModMobEffects.register();
        ComplexBlocks.register();
        ModPotions.register();
        ModContainerTypes.register();
        ModEntityType.register();
        ModBlockEntityTypes.register();
        ModRecipeSerializers.register();

    }

}
