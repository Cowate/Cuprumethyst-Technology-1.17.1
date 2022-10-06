package com.cowate.cuprumethyst.Block.PillagerStatue;

import com.cowate.cuprumethyst.Block.ComplexBlocks;
import com.cowate.cuprumethyst.Initailize.Messages;
import com.cowate.cuprumethyst.Initailize.ModMenuTypes;
import com.cowate.cuprumethyst.Utils.Data.ClientStatueMemory;
import com.cowate.cuprumethyst.Utils.Data.PillagerStatueMemoryManager;
import com.cowate.cuprumethyst.Utils.Data.PlayerStatueMemoryProvider;
import com.cowate.cuprumethyst.Utils.Network.PacketSyncStatueMemoryToClient;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;

import java.util.ArrayList;
import java.util.List;

public class PillagerStatueMenu extends AbstractContainerMenu {
    public static final int INGRIDENT_SLOT = 0;

    public final List<PillagerStatueMemoryManager.StatueInfo> statueList = new ArrayList<>();
    private final Container ingredientSlot = new SimpleContainer(1) {
        public void setChanged() {
            super.setChanged();
            PillagerStatueMenu.this.slotsChanged(this);
        }
    };
    private final ContainerLevelAccess access;


    public PillagerStatueMenu(int id, Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public PillagerStatueMenu(int id, Inventory inventory, final ContainerLevelAccess access) {
        super(ModMenuTypes.PILLAGER_STATUE.get(), id);
        this.access = access;

        this.addSlot(new Slot(this.ingredientSlot, 0, 11, 55));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j *18, 84 + i * 18));
            }
        }
        for (int k = 0 ; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k*18, 142));
        }

        statueList.clear();
        if (inventory.player.isLocalPlayer()) {

        } else {
            inventory.player.getCapability(PlayerStatueMemoryProvider.PLAYER_STATUE_MEMORY).ifPresent(playerStatueMemory -> {
                Messages.sendToPlayer(new PacketSyncStatueMemoryToClient(playerStatueMemory.statues), (ServerPlayer) inventory.player);
                for (Vec3i vec3i : playerStatueMemory.statues) {
                    statueList.add(PillagerStatueMemoryManager.get(inventory.player.level).getStatueInfo(vec3i));
                }
            });
        }

    }

    @Override
    public boolean clickMenuButton(Player player, int index) {
        if (player.isLocalPlayer()) {

        } else {
            player.getCapability(PlayerStatueMemoryProvider.PLAYER_STATUE_MEMORY).ifPresent(playerStatueMemory -> {
                Vec3i vec3i = playerStatueMemory.statues.get(index);
                player.teleportTo(vec3i.getX(), vec3i.getY(), vec3i.getZ());
            });
        }
        return true;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ComplexBlocks.PILLAGER_STATUE.get());
    }
}
