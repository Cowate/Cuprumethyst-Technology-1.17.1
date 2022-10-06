package com.cowate.cuprumethyst.Utils.Data;

import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PillagerStatueEvents {

    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerStatueMemoryProvider.PLAYER_STATUE_MEMORY).isPresent()) {
                event.addCapability(new ResourceLocation(Cuprumethyst.MOD_ID, "playerstatuememory"), new PlayerStatueMemoryProvider());
            }
        }
    }

    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerStatueMemoryProvider.PLAYER_STATUE_MEMORY).ifPresent(oldMemory -> {
                event.getPlayer().getCapability(PlayerStatueMemoryProvider.PLAYER_STATUE_MEMORY).ifPresent(newMemory ->{
                    newMemory.copyFrom(oldMemory);
                });
            });
        }
    }

    public static void onRegisterCapability(RegisterCapabilitiesEvent event) {
        event.register(PlayerStatueMemory.class);
    }

}
