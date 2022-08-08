package com.cowate.cuprumethyst.Initailize;


import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierBlockEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;


public class ModBlockEntityTypes {

    public static final RegistryObject<BlockEntityType<SoulMixierBlockEntity>> SOUL_MIXIER = Registeries.BLOCK_ENTITIES.register(
            "soul_mixier",
            () -> BlockEntityType.Builder.of(SoulMixierBlockEntity::new, ComplexBlocks.SOUL_MIXIER.get()).build(null)
    );

    public static void register() {

    }

}
