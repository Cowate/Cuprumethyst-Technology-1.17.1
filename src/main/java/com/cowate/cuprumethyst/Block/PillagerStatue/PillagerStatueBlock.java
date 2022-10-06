package com.cowate.cuprumethyst.Block.PillagerStatue;

import com.cowate.cuprumethyst.Utils.Data.PillagerStatueMemoryManager;
import com.cowate.cuprumethyst.Utils.Data.PlayerStatueMemoryProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class PillagerStatueBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
            Block.box(6.0D, 2.0D, 6.0D, 10.0D, 3.0D, 10.0D)
    );

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public PillagerStatueBlock(Properties properties) {
        super(properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PillagerStatueBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {

            String name = ((PillagerStatueBlockEntity)level.getBlockEntity(pos)).getCustomName().getContents();
            PillagerStatueMemoryManager.get(level).updateStatue(level);
            PillagerStatueMemoryManager.get(level).addStatue(name, pos);
            player.getCapability(PlayerStatueMemoryProvider.PLAYER_STATUE_MEMORY).ifPresent(playerStatueMemory -> {
                if (playerStatueMemory.addStatue(pos)) {
                    player.sendMessage(new TranslatableComponent("message.statue_added").withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                }
                playerStatueMemory.updateFromManager(PillagerStatueMemoryManager.get(level));
            });
            player.openMenu(state.getMenuProvider(level, pos));
            return InteractionResult.CONSUME;
        }

    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof PillagerStatueBlockEntity) {
            Component component = ((Nameable)blockEntity).getDisplayName();
            return new SimpleMenuProvider((id, inventory, player) ->
                    new PillagerStatueMenu(id, inventory, ContainerLevelAccess.create(level, pos)), component);
        } else {
            return null;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof PillagerStatueBlockEntity) {
                ((PillagerStatueBlockEntity)blockEntity).setCustomName(itemStack.getHoverName());
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState blockState, boolean b) {
        if (!state.is(blockState.getBlock())) {
            PillagerStatueMemoryManager.get(level).removeStatue(pos);
            super.onRemove(state, level, pos, blockState, b);
        }
    }

    @Override
    public boolean isPathfindable(BlockState p_60475_, BlockGetter p_60476_, BlockPos p_60477_, PathComputationType p_60478_) {
        return false;
    }
}
