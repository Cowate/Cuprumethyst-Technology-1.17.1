package com.cowate.cuprumethyst.Utils.Data;

import net.minecraft.core.Vec3i;

import java.util.List;

public class ClientStatueMemory {

    private static List<Vec3i> statues;
    private static int length;

    public static void set(List<Vec3i> list) {
        ClientStatueMemory.statues = list;
        ClientStatueMemory.length = list.size();
    }

    public static int getLength() {
        return length;
    }

    public static List<Vec3i> getStatues() {
        return statues;
    }

}
