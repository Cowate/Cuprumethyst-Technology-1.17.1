package com.cowate.cuprumethyst.Block.PillagerStatue;

import com.cowate.cuprumethyst.Cuprumethyst;
import com.cowate.cuprumethyst.Utils.Data.ClientStatueMemory;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PillagerStatueScreen extends AbstractContainerScreen<PillagerStatueMenu> {

    private static final ResourceLocation PILLAGER_STATUE_SCREEN = new ResourceLocation(Cuprumethyst.MOD_ID, "textures/gui/container/pillager_statue.png");
    private int moffset;

    public PillagerStatueScreen(PillagerStatueMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        moffset = 0;
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
    public boolean mouseClicked(double x, double y, int type) {
        int left = (this.width - this.imageWidth) / 2;
        int top = (this.height - this.imageHeight) / 2;

        for (int i = 0; i < 4; ++i) {
            double rx = x - (double)(left + 34);
            double ry = y - (double)(top + 17 + 15 * i);
            if (rx >= 0.0D && rx < 108.0D && ry >= 0.0D && ry < 15.0D && this.menu.clickMenuButton(this.minecraft.player, i)) {
                this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, i);
                return true;
            }
        }

        return super.mouseClicked(x, y, type);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int x, int y) {
        this.font.draw(poseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, PILLAGER_STATUE_SCREEN);

        int mleft = (this.width - this.imageWidth) / 2;
        int mtop = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, mleft, mtop, 0, 0, this.imageWidth, this.imageHeight);
        int length = ClientStatueMemory.getLength();
        List<Vec3i> list = ClientStatueMemory.getStatues();
        for (int i1 = 0; i1 < 4; ++i1) {
            int bleft = mleft +34;
            int btop = mtop + 17;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PILLAGER_STATUE_SCREEN);
            if (moffset + i1 < length) {
                String s = list.get(i1 + moffset).toShortString();
                Component XYZ = new TextComponent(s);
                this.blit(poseStack, bleft, btop + i1 * 15, 0, 167, 108, 15);
                this.font.draw(poseStack, XYZ, bleft + 5, btop + i1 * 15 + 4, 14869218);
                this.font.drawShadow(poseStack, s, bleft + 5, btop + i1 * 15 + 4, 6118749);
            }
            else {
                this.blit(poseStack, bleft, btop + i1 * 15, 0, 182, 108, 15);
            }

        }


    }
}
