package com.cowate.cuprumethyst.Block;


import com.cowate.cuprumethyst.Block.PillagerStatue.PillagerStatueBlock;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierBlock;
import com.cowate.cuprumethyst.Initailize.Registeries;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class ComplexBlocks{

    public static final RegistryObject<SoulMixierBlock> SOUL_MIXIER = register(
            "soul_mixier",
            () -> new SoulMixierBlock(BlockBehaviour.Properties
                    .copy(Blocks.BREWING_STAND)),
            CreativeModeTab.TAB_BREWING
    );
    public static final RegistryObject<PillagerStatueBlock> PILLAGER_STATUE = register(
            "pillager_statue",
            () -> new PillagerStatueBlock(BlockBehaviour.Properties
                    .of(Material.HEAVY_METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F)),
            CreativeModeTab.TAB_MISC
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

    public static VoxelShape calculateShapes(Direction direction, VoxelShape shape) {
        final VoxelShape[] buffer = {shape , Shapes.empty()};
        final int times = (direction.get2DDataValue() - Direction.EAST.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; ++i) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(
                    buffer[1], Shapes.create(1 - maxZ, minY, minX, 1- minZ, maxY, maxX)
            ));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }

}
