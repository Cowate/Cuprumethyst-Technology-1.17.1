package com.cowate.cuprumethyst.Data.Client;

import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Cuprumethyst.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("raw_cuprumethyst_block", modLoc("block/raw_cuprumethyst_block"));
        withExistingParent("cuprumethyst_block", modLoc("block/cuprumethyst_block"));
        withExistingParent("soul_mixier", modLoc("block/soul_mixier"));
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "amethyst_dust");
        builder(itemGenerated, "oxidized_copper_ingot");
        builder(itemGenerated, "raw_cuprumethyst_ingot");
        builder(itemGenerated, "soul_powder");
        builder(itemGenerated, "soul_rod");
        builder(itemGenerated, "cuprumethyst_ingot");
        builder(itemGenerated, "pebble_stone");
        builder(itemGenerated, "pebble_blackstone");
        builder(itemGenerated, "sling");

        buildSlingInHand();

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder withoutExitingParent(String name){
        return getBuilder(name);
    }

    private void buildSlingInHand() {
        withoutExitingParent("sling_in_hand")
                .texture("sling", "cuprumethyst:item/sling_model")
                .element().from(6.0F, 0.0F, 3.0F).to(10.0F, 4.0F, 13.0F)
                .allFaces(((direction, faceBuilder) -> {
                    faceBuilder.texture("#sling");
                    switch (direction) {
                        case NORTH -> faceBuilder.uvs(5.0F, 5.0F, 7.0F, 7.0F);
                        case EAST -> faceBuilder.uvs(0.0F, 0.5F, 5.0F, 7.0F);
                        case SOUTH -> faceBuilder.uvs(0.0F, 0.0F, 2.0F, 2.0F);
                        case WEST -> faceBuilder.uvs(7.0F, 5.0F, 12.0F, 7.0F);
                        case UP -> faceBuilder.uvs(7.0F, 5.0F, 5.0F, 0.0F);
                        case DOWN -> faceBuilder.uvs(9.0F, 0.0F, 7.0F, 5.0F);
                    }
                })).end()
                .guiLight(BlockModel.GuiLight.FRONT)
                .transforms()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .translation(0.0F, 4.6F, 0.0F).scale(1.1F).end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                .translation(0.0F, 4.6F, 0.0F).scale(1.1F).end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                .translation(0.0F, -3.25F, 0.0F).rotation(-90.0F, 0.0F, 0.0F).end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
                .translation(0.0F, -3.25F, 0.0F).rotation(-90.0F, 0.0F, 0.0F).end()
                .transform(ModelBuilder.Perspective.GUI)
                .translation(4.5F, 3.0F, 0.0F).scale(1.2F).rotation(-45.0F, 135.0F, 90.0F).end()
                .transform(ModelBuilder.Perspective.HEAD)
                .translation(0.0F, 14.0F, 0.0F).end()
                .end();
    }
}
