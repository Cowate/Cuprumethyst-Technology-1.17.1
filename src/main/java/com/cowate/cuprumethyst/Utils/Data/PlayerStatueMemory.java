package com.cowate.cuprumethyst.Utils.Data;

import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatueMemory {

    public List<Vec3i> statues = new ArrayList<>();
    public boolean addStatue(Vec3i pos) {
        for (Vec3i vec3i : statues) {
            if (pos.getX() == vec3i.getX() && pos.getY() == vec3i.getY() && pos.getZ() == vec3i.getZ()) {
                return false;
            }
        }
        statues.add(pos);
        return true;
    }

    public int updateFromManager(PillagerStatueMemoryManager manager) {
        int l = statues.size();
        for (int i = l - 1; i >= 0; --i) {
            if (!manager.checkStatue(statues.get(i))) {
                statues.remove(i);
            }
        }
        return l - statues.size();
    }

    public void saveNBTData(CompoundTag tag) {
        ListTag list = new ListTag();
        statues.forEach(vec3i -> {
            CompoundTag newTag = new CompoundTag();
            newTag.putInt("x", vec3i.getX());
            newTag.putInt("y", vec3i.getY());
            newTag.putInt("z", vec3i.getZ());
            list.add(newTag);
        });
        tag.put("statues", list);
        tag.putInt("length", list.size());
    }

    public void loadNBTData(CompoundTag tag) {
        statues.clear();
        tag.getInt("length");
        ListTag list = tag.getList("statues", Tag.TAG_COMPOUND);
        for (Tag tag1 : list) {
            CompoundTag compoundTag = (CompoundTag)tag1;
            statues.add(new Vec3i(compoundTag.getInt("x"), compoundTag.getInt("y"), compoundTag.getInt("z")));
        }
    }

    public void copyFrom(PlayerStatueMemory oldMemory) {
        statues = oldMemory.statues;
    }
}
