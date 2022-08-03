package com.cowate.cuprumethyst.Initailize;

import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.awt.*;

public class ModContainerTypes {
    public static final RegistryObject<MenuType<SoulMixierMenu>> SOUL_MIXIER = Registeries.CONTAINERS.register(
            "soul_mixier", () -> new MenuType<>(SoulMixierMenu::new));


    public static void register() {

    }
}
