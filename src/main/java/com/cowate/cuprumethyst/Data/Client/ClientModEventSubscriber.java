package com.cowate.cuprumethyst.Data.Client;


import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Initailize.ModEntityType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cuprumethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //event.registerLayerDefinition();
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityType.PEBBLE.get(), ThrownItemRenderer::new);

    }

    @SubscribeEvent
    public static void onRegisterModel(ModelRegistryEvent event) {
        ModelLoader.addSpecialModel(new ModelResourceLocation("cuprumethyst:sling_in_hand#inventory"));
    }

    @SubscribeEvent
    public static void onBakeModel(ModelBakeEvent event) {

    }
}
