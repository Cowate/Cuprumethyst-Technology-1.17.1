package com.cowate.cuprumethyst.Block.SoulMixier;


import com.cowate.cuprumethyst.Cuprumethyst;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoulMixierScreen extends AbstractContainerScreen<SoulMixierMenu> {
    private static final ResourceLocation SOUL_MIXIER_LOCATION = new ResourceLocation(Cuprumethyst.MOD_ID, "textures/gui/container/soul_mixier.png");


    public SoulMixierScreen(SoulMixierMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(PoseStack poseStack, int x, int y, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, x, y, partialTicks);
        this.renderTooltip(poseStack, x, y);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SOUL_MIXIER_LOCATION);


        int posX = (this.width - this.imageWidth) / 2;
        int posY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, posX, posY, 0, 0, this.imageWidth, this.imageHeight);
        int fuel = this.menu.getFuel();
        int len = Mth.clamp((18*fuel+19) / 40 , 0, 18);
        if (len > 0) {
            this.blit(poseStack, posX + 80 , posY + 63, 176, 23, len , 4);
        }
        int progress = this.menu.getMixingTicks();
        if (progress > 0) {
            int scaled = (int)(46.0F * ( 1 - ((float)progress / 400.0F)));
            this.blit(poseStack, posX + 65, posY + 39, 176, 0, scaled, 23);
        }

    }


}
