package com.cowate.cuprumethyst.Initailize;


import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntityTypes {
/*
    public static final RegistryObject<BlockEntityType<SoulMixierBlockEntity>> SOUL_MIXIER = Registeries.BLOCK_ENTITIES.register(
            "soul_mixier",
            () -> BlockEntityType.Builder.of(SoulMixierBlockEntity::new, ComplexBlocks.SOUL_MIXIER.get()).build(null)
    );
*/
    public static final RegistryObject<BlockEntityType<SoulMixierBlockEntity>> SOUL_MIXIER = registry(
            "soul_mixier",
            BlockEntityType.Builder.of(SoulMixierBlockEntity::new, ComplexBlocks.SOUL_MIXIER.get())
    );

    public static void register() {

    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registry(String name, BlockEntityType.Builder<T> builder){
        return Registeries.BLOCK_ENTITIES.register(
                name , () -> {
                    return builder.build(null);
                }
        );
    }


}
