package com.cowate.cuprumethyst.Block.SoulMixier;

import com.cowate.cuprumethyst.Initailize.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SoulMixierBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty[] HAS_BOTTLE = new BooleanProperty[]{
            BlockStateProperties.HAS_BOTTLE_0,
            BlockStateProperties.HAS_BOTTLE_1,
            BlockStateProperties.HAS_BOTTLE_2
    };
    protected static final VoxelShape SHAPE = Shapes.or(
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(2.0D, 2.0D, 6.0D, 14.0D, 14.0D, 10.0D)
    );

    public SoulMixierBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any()
                .setValue(HAS_BOTTLE[0], Boolean.valueOf(false))
                .setValue(HAS_BOTTLE[1], Boolean.valueOf(false))
                .setValue(HAS_BOTTLE[2], Boolean.valueOf(false))
        );
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SoulMixierBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlockEntityTypes.SOUL_MIXIER.get(), SoulMixierBlockEntity::serverTick);
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPE;
    }
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide){
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SoulMixierBlockEntity) {
                player.openMenu((SoulMixierBlockEntity)blockEntity);
                //awardStat;
            }
            return InteractionResult.CONSUME;
        }
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState blockState, boolean bool) {
        if (!state.is(blockState.getBlock())){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SoulMixierBlockEntity){
                if (((SoulMixierBlockEntity) blockEntity).dataAccess.get(0) > 0) {
                    ((SoulMixierBlockEntity) blockEntity).setItem(0, new ItemStack(Items.GLASS_BOTTLE));
                    ((SoulMixierBlockEntity) blockEntity).setItem(1, new ItemStack(Items.GLASS_BOTTLE));
                }
                Containers.dropContents(level, pos, (SoulMixierBlockEntity)blockEntity);
            }
            super.onRemove(state, level, pos, blockState, bool);
        }
    }

    public boolean hasAnalogOutputSignal(BlockState p_60457_) {
        return true;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SoulMixierBlockEntity) {
                ((SoulMixierBlockEntity) blockEntity).setCustomName(itemStack.getDisplayName());
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2]);
    }

    public boolean isPathfindable(BlockState p_60475_, BlockGetter p_60476_, BlockPos p_60477_, PathComputationType p_60478_) {
        return false;
    }
}
