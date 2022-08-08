package com.cowate.cuprumethyst.Block.SoulMixier;

import com.cowate.cuprumethyst.Data.server.recipes.PotionMixing;
import com.cowate.cuprumethyst.Initailize.ModBlockEntityTypes;
import com.cowate.cuprumethyst.Initailize.ModRecipeSerializers;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


import javax.annotation.Nullable;
import java.util.Arrays;

public class SoulMixierBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    private static final int POTION_0 = 0;
    private static final int POTION_1 = 1;
    private static final int OUTPUT_POTION = 2;
    private static final int FUEL_SLOT = 3;
    private static final int[] SLOT_FOR_UP = new int[]{0, 1, 3};
    private static final int[] SLOT_FOR_SIDES = new int[]{0, 1, 2};
    private static final int[] SLOT_FOR_DOWN = new int[]{2};
    private static final int[] SLOT_FOR_NONE = new int[]{};
    public static final int MAX_FUEL = 40;
    public static final int FUEL_INC = 20;
    int fuel;
    int mixTime;
    private boolean[] lastPotionCount;
    private Item potion0;
    private Item potion1;
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private final RecipeType<?> recipeType;
    public SoulMixierBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntityTypes.SOUL_MIXIER.get(), pos, state);
        this.recipeType = ModRecipeSerializers.Types.MIXING;
    }
    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.UP) {
            return  SLOT_FOR_UP;
        } else {
            if (mixTime > 0) return SLOT_FOR_NONE;
            return direction == Direction.DOWN ? SLOT_FOR_DOWN : SLOT_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack itemStack) {
        if (index == 0 || index == 1) {
            return true;
        } else if (index == 3) {
            return itemStack.is(SimpleItems.SOUL_POWDER.get());
        } else {
            return itemStack.is(Items.GLASS_BOTTLE);
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack itemStack, Direction direction) {
        return mixTime <= 0;
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container.cuprumethyst.soul_mixier");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new SoulMixierMenu(id, inventory, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.items) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> SoulMixierBlockEntity.this.mixTime;
                case 1 -> SoulMixierBlockEntity.this.fuel;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    SoulMixierBlockEntity.this.mixTime = value;
                case 1:
                    SoulMixierBlockEntity.this.fuel = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public static void serverTick(Level level, BlockPos pos, BlockState state, SoulMixierBlockEntity entity){
        ItemStack itemStack = entity.items.get(FUEL_SLOT);
        if (entity.fuel <= 20 && itemStack.is(SimpleItems.SOUL_POWDER.get())){
            entity.fuel += 20;
            itemStack.shrink(1);
            setChanged(level, pos, state);
        }
        boolean can_mix = entity.isMixable(entity.items);
        boolean is_mixing = entity.mixTime > 0;
        ItemStack itemStack0 = entity.items.get(0);
        ItemStack itemStack1 = entity.items.get(1);

        if (is_mixing){
            entity.mixTime--;
            boolean is_mixed = entity.mixTime == 0;
            if (is_mixed && can_mix){
                doMix(level, pos, entity.items);
                setChanged(level, pos, state);
            } else if (!can_mix || !itemStack0.is(entity.potion0) || !itemStack1.is(entity.potion1)){
                entity.mixTime = 0;
                setChanged(level, pos, state);
            }
        } else if (can_mix && entity.fuel > 0){
            --entity.fuel;
            entity.mixTime = 400;
            entity.potion0 = itemStack0.getItem();
            entity.potion1 = itemStack1.getItem();

            setChanged(level, pos, state);
        }

        boolean [] potionbit = entity.getPotionBits();
        if (!Arrays.equals(potionbit, entity.lastPotionCount)){
            entity.lastPotionCount = potionbit;
            BlockState blockState = state;
            if (!(state.getBlock() instanceof SoulMixierBlock)){
                return ;
            }

            for (int i = 0; i < SoulMixierBlock.HAS_BOTTLE.length; ++i){
                blockState = blockState.setValue(SoulMixierBlock.HAS_BOTTLE[i], potionbit[i]);
            }

            level.setBlock(pos, blockState, 2);
        }


    }


    @Override
    public ItemStack getItem(int index) {
        return index >= 0 && index < this.items.size() ? this.items.get(index) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        if (index >= 0 && index < this.items.size()) {
            this.items.set(index, itemStack);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr(
                    (double)this.worldPosition.getX()+0.5D,
                    (double)this.worldPosition.getY()+0.5D,
                    (double)this.worldPosition.getZ()+0.5D
                    ) > 64.0D
            );
        }
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    private boolean isMixable(NonNullList<ItemStack> list){
        ItemStack input0 = list.get(0);
        ItemStack input1 = list.get(1);
        ItemStack output = list.get(2);

        if (input1.isEmpty() || input0.isEmpty() || !output.is(Items.GLASS_BOTTLE)) {
            return false;
        } else {
            return PotionMixing.onRecipes(input0, input1);
        }
    }

    private static void doMix(Level level, BlockPos pos, NonNullList<ItemStack> list){
        if (PotionMixing.onRecipes(list.get(0), list.get(1))) {
            list.set(2, PotionMixing.getOutput(list.get(0), list.get(1)));
            list.set(0, new ItemStack(Items.GLASS_BOTTLE));
            list.set(1, new ItemStack(Items.GLASS_BOTTLE));
        }
        //~ Server Sound Event
    }

    private boolean[] getPotionBits(){
        boolean[] potionbits = new boolean[3];

        for (int i = 0; i < 3; ++i){
            if (!this.items.get(i).isEmpty()){
                potionbits[i] = true;
            }
        }
        return potionbits;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.mixTime = tag.getShort("MixTime");
        this.fuel = tag.getByte("Fuel");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
       super.save(tag);
        tag.putShort("MixTime", (short)this.mixTime);
        ContainerHelper.saveAllItems(tag, this.items);
        tag.putByte("Fuel", (byte)this.fuel);
        return tag;
    }
}
