package com.cowate.cuprumethyst.Block.PillagerStatue;

import com.cowate.cuprumethyst.Initailize.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PillagerStatueBlockEntity extends BlockEntity implements Nameable {
    /*
    make a rotating totem of undying
    */
    private Component name;

    public PillagerStatueBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PILLAGER_STATUE.get(), pos, state);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        if (this.hasCustomName()) {
            tag.putString("CustomName", Component.Serializer.toJson(this.name));
        }
        return tag;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
    }

    @Override
    public Component getName() {
        return (Component)(this.name != null ? this.name : new TranslatableComponent("container.cuprumethyst.pillager_statue_nameless"));
    }

    public void setCustomName(Component name) {
        this.name = name;
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return this.name;
    }
}
