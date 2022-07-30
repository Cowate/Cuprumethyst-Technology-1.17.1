package com.cowate.cuprumethyst.Block.SoulMixier;

import com.cowate.cuprumethyst.Data.server.MixingTech.MixingRecipeRegistry;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class SoulMixierMenu extends AbstractContainerMenu {
    private static final int INPUT_0 = 0;
    private static final int INPUT_1 = 1;
    private static final int OUTPUT = 2;
    private static final int FUEL_SLOT = 3;
    private static final int INV_SLOT_START = 4;
    private static final int INV_SLOT_END = 30;
    private static final int USE_ROW_SLOT_START = 31;
    private static final int USE_ROW_SLOT_END = 39;

    private final Container soulMixier;

    private final ContainerData soulMixierData;

    public SoulMixierMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(4), new SimpleContainerData(2));
    }

    public SoulMixierMenu(int id, Inventory inventory, Container container, ContainerData data) {
        super(MenuType.BREWING_STAND, id);
        checkContainerSize(container, 5);
        checkContainerDataCount(data, 2);
        this.soulMixier = container;
        this.soulMixierData = data;
        this.addSlot(new SoulMixierMenu.PotionSlot(container, 0, 47, 32));
        this.addSlot(new SoulMixierMenu.PotionSlot(container, 1, 47, 52));
        this.addSlot(new SoulMixierMenu.OutputSlot(container, 2, 116, 42));
        this.addSlot(new SoulMixierMenu.FuelSlot(container, 3, 82, 22));
        this.addDataSlots(data);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

    }

    @Override
    public boolean stillValid(Player player) {
        return this.soulMixier.stillValid(player);
    }

    public int getFuel() {
        return this.soulMixierData.get(1);
    }

    public int getMixingTicks() {
        return this.soulMixierData.get(0);
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack chosen = slot.getItem();
            itemStack = chosen.copy();
            if (index > 3 || index < 0) {
                if (SoulMixierMenu.FuelSlot.mayPlaceItem(itemStack)){
                    if (!this.moveItemStackTo(chosen, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (SoulMixierMenu.PotionSlot.mayPlaceItem(chosen)) {
                    if (!this.moveItemStackTo(chosen, 0, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (SoulMixierMenu.OutputSlot.mayPlaceItem(chosen)) {
                    if (!this.moveItemStackTo(chosen, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index > 3 && index < 31) {
                    if (!this.moveItemStackTo(chosen, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index > 30 && index < 40) {
                    if (!this.moveItemStackTo(chosen, 4, 31, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                if (!this.moveItemStackTo(chosen, 4, 40, false)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(chosen, itemStack);
            }
            if (chosen.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (chosen.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, chosen);
        }
        return itemStack;
    }

    static class OutputSlot extends Slot {
        public OutputSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }
        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return mayPlaceItem(itemStack);
        }
        public static boolean mayPlaceItem(ItemStack itemStack) {
            return MixingRecipeRegistry.isValidOutput(itemStack);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }
    }
    static class FuelSlot extends Slot {
        public FuelSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }
        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return mayPlaceItem(itemStack);
        }
        public static boolean mayPlaceItem(ItemStack itemStack) {
            return itemStack.is(SimpleItems.AMETHYST_DUST.get());
        }
        @Override
        public int getMaxStackSize() {
            return 64;
        }
    }
    static class PotionSlot extends Slot {
        public PotionSlot (Container container, int index, int x, int y) {
            super(container, index, x, y);
        }

        public boolean mayPlace(ItemStack itemStack) {
            return mayPlaceItem(itemStack);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        public void onTake(Player player, ItemStack itemStack) {
            // ???
        }

        public static boolean mayPlaceItem(ItemStack itemStack) {
            return MixingRecipeRegistry.isValidInput(itemStack);
        }
    }
}
