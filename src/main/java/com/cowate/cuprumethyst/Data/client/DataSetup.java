package com.cowate.cuprumethyst.Data.client;

import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierMenu;
import com.cowate.cuprumethyst.Block.SoulMixier.SoulMixierScreen;
import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Initailize.ModContainerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Cuprumethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class DataSetup {
    private DataSetup() {
    }



}
