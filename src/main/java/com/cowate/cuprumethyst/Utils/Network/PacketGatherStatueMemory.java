package com.cowate.cuprumethyst.Utils.Network;

import com.cowate.cuprumethyst.Utils.Data.PlayerStatueMemoryProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketGatherStatueMemory {

    public PacketGatherStatueMemory() {
    }

    public PacketGatherStatueMemory(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // do something
            ServerPlayer player = ctx.getSender();

        });
        return true;
    }
}
