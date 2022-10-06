package com.cowate.cuprumethyst.Utils.Data;

import com.cowate.cuprumethyst.Block.PillagerStatue.PillagerStatueBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.ArrayList;
import java.util.List;

public class PillagerStatueMemoryManager extends SavedData {

    private final List<StatueInfo> statueList = new ArrayList<>();

    public static PillagerStatueMemoryManager get(Level level) {
        if (level.isClientSide) {
            throw new RuntimeException("Don't access this in Client-side");
        }
        DimensionDataStorage storage = ((ServerLevel)level).getDataStorage();
        return storage.computeIfAbsent(PillagerStatueMemoryManager::new, PillagerStatueMemoryManager::new, "pillagerstatuemanager");
    }

    public PillagerStatueMemoryManager() {

    }

    public PillagerStatueMemoryManager(CompoundTag tag) {
        ListTag listTag = tag.getList("statue", Tag.TAG_COMPOUND);
        for (Tag t : listTag) {
            CompoundTag tagInfo = (CompoundTag)t;
            Vec3i vec3i = new Vec3i(tagInfo.getInt("x"), tagInfo.getInt("y"), tagInfo.getInt("z"));
            String name = tagInfo.getString("name");
            statueList.add(new StatueInfo(name, vec3i));
        }
    }

    public boolean addStatue(String str, Vec3i vec3i) {
        for (StatueInfo si : statueList) {
            if (si.getPos().getX() == vec3i.getX() && si.getPos().getZ() == vec3i.getZ() && si.getPos().getY() == vec3i.getY())
                return false;
        }
        statueList.add(new StatueInfo(str, vec3i));
        setDirty();
        return true;
    }

    public boolean checkStatue(Vec3i vec3i) {
        for (StatueInfo si : statueList) {
            if (si.contains(vec3i)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeStatue(Vec3i vec3i) {
        for (StatueInfo si : statueList) {
            if (si.contains(vec3i)) {
                statueList.remove(si);
                return true;
            }
        }
        return false;
    }

    public StatueInfo getStatueInfo(Vec3i vec3i) {
        for (StatueInfo si : statueList) {
            if (si.contains(vec3i)) {
                return si;
            }
        }
        return null;
    }

    public int updateStatue(Level level) {
        int count = 0;
        for (int i = statueList.size() - 1; i >= 0; --i) {
            if (!(level.getBlockEntity(new BlockPos(statueList.get(i).getPos())) instanceof PillagerStatueBlockEntity)) {
                statueList.remove(i);
                count++;
            }
        }
        setDirty();
        return count;
    }


    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        statueList.forEach(info -> {
            CompoundTag newTag = new CompoundTag();
            newTag.putInt("x", info.pos.getX());
            newTag.putInt("y", info.pos.getY());
            newTag.putInt("z", info.pos.getZ());
            newTag.putString("name", info.name);
            list.add(newTag);
        });
        tag.put("statue", list);
        return tag;
    }

    public class StatueInfo {
        public Vec3i pos;
        public String name;

        public StatueInfo(String str, double x, double y, double z) {
            pos = new Vec3i(x, y, z);
            name = str;
        }
        public StatueInfo(String str, int x, int y, int z) {
            pos = new Vec3i(x, y, z);
            name = str;
        }
        public StatueInfo(String str, Vec3i vec3i) {
            pos = vec3i;
            name = str;
        }

        public String getName() {
            return name;
        }

        public Vec3i getPos() {
            return pos;
        }

        public boolean contains(Vec3i vec3i) {
            return vec3i.getZ() == pos.getZ() && vec3i.getY() == pos.getY() && vec3i.getX() == pos.getX();
        }
    }
 }
