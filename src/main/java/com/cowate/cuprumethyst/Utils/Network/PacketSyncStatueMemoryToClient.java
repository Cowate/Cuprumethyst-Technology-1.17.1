package com.cowate.cuprumethyst.Utils.Network;

import com.cowate.cuprumethyst.Utils.Data.ClientStatueMemory;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PacketSyncStatueMemoryToClient {

    private final List<Vec3i> statues;
    private final int length;

    public PacketSyncStatueMemoryToClient(List<Vec3i> list) {
        statues = list;
        length = list.size();
    }

    public PacketSyncStatueMemoryToClient(FriendlyByteBuf buf) {
        length = buf.readInt();
        statues = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            int x = buf.readInt();
            int y = buf.readInt();
            int z = buf.readInt();
            statues.add(new Vec3i(x, y, z));
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(length);
        for (int i = 0; i < length; i++) {
            Vec3i vec3i = statues.get(i);
            buf.writeInt(vec3i.getX());
            buf.writeInt(vec3i.getY());
            buf.writeInt(vec3i.getZ());
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientStatueMemory.set(statues);
        });
        return true;
    }
}
