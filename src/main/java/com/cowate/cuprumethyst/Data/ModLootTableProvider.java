package com.cowate.cuprumethyst.Data;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Block.SimpleBlocks;
import com.cowate.cuprumethyst.Initailize.Registeries;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK)
                //Entity, Chest ...
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> {
            LootTables.validate(validationtracker, p_218436_2_, p_218436_3_);
        });
    }

    public static class ModBlockLootTables extends BlockLoot{
        @Override
        protected void addTables() {
            dropSelf(SimpleBlocks.RAW_CUPRUMETHYST_BLOCK.get());
            dropSelf(SimpleBlocks.CUPRUMETHYST_BLOCK.get());
            add(ComplexBlocks.SOUL_MIXIER.get(), BlockLoot::createNameableBlockEntityTable);
            add(ComplexBlocks.PILLAGER_STATUE.get(), BlockLoot::createNameableBlockEntityTable);
        }
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registeries.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
