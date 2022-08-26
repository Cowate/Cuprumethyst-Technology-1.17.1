package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Entity.Projectile.Pebble;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModEntityType {
    public static final RegistryObject<EntityType<Pebble>> PEBBLE = Registeries.ENTITIES.register(
            "pebble", () -> EntityType.Builder.<Pebble>of(Pebble::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(new ResourceLocation(Cuprumethyst.MOD_ID, "pebble_stone").toString())
    );

    public static void register() {
    }
}
