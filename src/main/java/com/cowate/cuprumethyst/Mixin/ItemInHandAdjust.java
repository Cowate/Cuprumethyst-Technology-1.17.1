package com.cowate.cuprumethyst.Mixin;

import com.cowate.cuprumethyst.Item.ComplexItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(ItemRenderer.class)
public class ItemInHandAdjust {

    @Shadow @Final private ItemModelShaper itemModelShaper;
    public ItemStack itemStack;

    public boolean render_flag;
    public ItemStack render_itemStack;

    @Inject(method = "getModel",
            at = @At("HEAD"))
    private void getItemStack(ItemStack p_174265_, Level p_174266_, LivingEntity p_174267_, int p_174268_, CallbackInfoReturnable<BakedModel> cir) {
        itemStack = p_174265_;
    }

    @ModifyArg(method = "getModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/model/ItemOverrides;resolve(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/LivingEntity;I)Lnet/minecraft/client/resources/model/BakedModel;"), index = 0)
    private BakedModel modifyModelInHand(BakedModel bakedModel) {
        if (itemStack.is(ComplexItems.SLING.get())) {
            BakedModel bakedModel1 = this.itemModelShaper.getModelManager().getModel(new ModelResourceLocation("cuprumethyst:sling_in_hand#inventory"));
            return bakedModel1;
        }
        return bakedModel;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;handleCameraTransforms(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Z)Lnet/minecraft/client/resources/model/BakedModel;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void getFlag(ItemStack p_115144_, ItemTransforms.TransformType p_115145_, boolean p_115146_, PoseStack p_115147_, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_, CallbackInfo ci, boolean flag) {
        render_flag = flag;
        render_itemStack = p_115144_;
    }

    @ModifyArg(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;handleCameraTransforms(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Z)Lnet/minecraft/client/resources/model/BakedModel;"), index = 1)
    private BakedModel addModel(BakedModel bakedModel){
        if (render_flag) {
            if (render_itemStack.is(ComplexItems.SLING.get())) {
                BakedModel bakedModel1 = this.itemModelShaper.getModelManager().getModel(new ModelResourceLocation("cuprumethyst:sling#inventory"));
                return bakedModel1;
            }
        }
        return bakedModel;
    }

}
