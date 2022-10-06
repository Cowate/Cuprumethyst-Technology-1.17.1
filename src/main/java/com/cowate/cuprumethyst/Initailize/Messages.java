package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Utils.Network.PacketGatherStatueMemory;
import com.cowate.cuprumethyst.Utils.Network.PacketSyncStatueMemoryToClient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class Messages {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Cuprumethyst.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketGatherStatueMemory.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketGatherStatueMemory::new)
                .encoder(PacketGatherStatueMemory::toBytes)
                .consumer(PacketGatherStatueMemory::handle)
                .add();

        net.messageBuilder(PacketSyncStatueMemoryToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncStatueMemoryToClient::new)
                .encoder(PacketSyncStatueMemoryToClient::toBytes)
                .consumer(PacketSyncStatueMemoryToClient::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
