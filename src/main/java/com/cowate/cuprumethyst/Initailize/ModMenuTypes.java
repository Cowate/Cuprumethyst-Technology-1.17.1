package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Block.PillagerStatue.PillagerStatueMenu;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModMenuTypes {
    public static final RegistryObject<MenuType<SoulMixierMenu>> SOUL_MIXIER = Registeries.CONTAINERS.register(
            "soul_mixier", () -> new MenuType<>(SoulMixierMenu::new));
    public static final RegistryObject<MenuType<PillagerStatueMenu>> PILLAGER_STATUE = Registeries.CONTAINERS.register(
            "pillager_statue", () -> new MenuType<>(PillagerStatueMenu::new));

    public static void register() {

    }
}
