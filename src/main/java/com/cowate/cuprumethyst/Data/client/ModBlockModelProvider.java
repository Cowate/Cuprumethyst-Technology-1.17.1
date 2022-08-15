package com.cowate.cuprumethyst.Data.client;

import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;

import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, Cuprumethyst.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        soulMixierModel();

    }
    public BlockModelBuilder withoutExitingParent(String name){
        return getBuilder(name);
    }

    private void soulMixierBottle(String name, float[] fromto, float[] uvss) {
        ModelBuilder<BlockModelBuilder>.ElementBuilder this_bottle = withoutExitingParent(name)
                .texture("2", "minecraft:block/brewing_stand")
                .texture("particle", "minecraft:block/polished_blackstone")
                .element().from(fromto[0], fromto[1], fromto[2]).to(fromto[3], fromto[4], fromto[5]);
        this_bottle.face(Direction.EAST)
                .uvs(uvss[0], uvss[1], uvss[2], uvss[3]).texture("#2");
        this_bottle.face(Direction.WEST)
                .uvs(uvss[4], uvss[5], uvss[6], uvss[7]).texture("#2");
    }

    private void soulMixierModel() {
        withoutExitingParent("soul_mixier")
                .texture("0", "minecraft:block/polished_blackstone_bricks")
                .texture("1", "minecraft:block/polished_blackstone")
                .texture("2", "minecraft:block/brewing_stand")
                .texture("particle", "minecraft:block/polished_blackstone")
                        .element().from(0.0F, 0.0F, 0.0F).to(16.0F, 1.0F, 16.0F)
                        .allFaces((direction, faceBuilder) -> {
                            faceBuilder.texture("#0");
                            switch (direction) {
                                case NORTH -> faceBuilder.uvs(0.0F, 6.0F, 16.0F, 7.0F);
                                case EAST -> faceBuilder.uvs(1.0F, 0.0F, 2.0F, 16.0F).rotation(ModelBuilder.FaceRotation.CLOCKWISE_90);
                                case SOUTH -> faceBuilder.uvs(0.0F, 13.0F, 16.0F, 14.0F);
                                case WEST -> faceBuilder.uvs(14.0F, 0.0F, 15.0F, 16.0F).rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90);
                                case UP, DOWN -> faceBuilder.uvs(0.0F, 0.0F, 16.0F, 16.0F);
                            }
                        }).end()
                        .element().from(2.0F, 1.0F, 6.0F).to(14.0F, 14.0F, 10.0F)
                        .allFaces((direction, faceBuilder) -> {
                            faceBuilder.texture("#1");
                            switch (direction) {
                                case NORTH, SOUTH -> faceBuilder.uvs(3.0F, 4.0F, 15.0F, 15.0F);
                                case EAST -> faceBuilder.uvs(1.0F, 3.0F, 5.0F, 15.0F);
                                case WEST -> faceBuilder.uvs(11.0F, 1.0F, 15.0F, 12.0F);
                                case UP -> faceBuilder.uvs(2.0F, 2.0F, 14.0F, 6.0F);
                                case DOWN -> faceBuilder.uvs(1.0F, 13.0F, 13.0F, 15.0F);
                            }
                        }).end()
                        .element().from(11.0F, 1.0F, 2.0F).to(13.0F, 2.0F, 6.0F)
                        .allFaces((direction, faceBuilder) -> {
                            faceBuilder.texture("#1");
                            switch (direction) {
                                case NORTH -> faceBuilder.uvs(2.0F, 3.0F, 4.0F, 4.0F);
                                case EAST -> faceBuilder.uvs(1.0F, 2.0F, 5.0F, 3.0F);
                                case SOUTH -> faceBuilder.uvs(4.0F, 6.0F, 6.0F, 7.0F);
                                case WEST -> faceBuilder.uvs(3.0F, 3.0F, 7.0F, 4.0F);
                                case UP -> faceBuilder.uvs(10.0F, 9.0F, 12.0F, 13.0F);
                                case DOWN -> faceBuilder.uvs(7.0F, 10.0F, 9.0F, 14.0F);
                            }
                        }).end()
                        .element().from(3.0F, 1.0F, 2.0F).to(5.0F, 2.0F, 6.0F)
                        .allFaces((direction, faceBuilder) -> {
                            faceBuilder.texture("#1");
                            switch (direction) {
                                case NORTH -> faceBuilder.uvs(2.0F, 3.0F, 4.0F, 4.0F);
                                case EAST -> faceBuilder.uvs(1.0F, 2.0F, 5.0F, 3.0F);
                                case SOUTH -> faceBuilder.uvs(4.0F, 6.0F, 6.0F, 7.0F);
                                case WEST -> faceBuilder.uvs(3.0F, 3.0F, 7.0F, 4.0F);
                                case UP -> faceBuilder.uvs(9.0F, 10.0F, 12.0F, 13.0F);
                                case DOWN -> faceBuilder.uvs(7.0F, 10.0F, 9.0F, 14.0F);
                            }
                        }).end()
                        .element().from(6.0F, 11.0F, 10.0F).to(10.0F, 13.0F, 14.0F)
                        .allFaces((direction, faceBuilder) -> {
                            faceBuilder.texture("#1");
                            switch (direction) {
                                case NORTH -> faceBuilder.uvs(5.0F, 3.0F, 9.0F, 5.0F);
                                case EAST -> faceBuilder.uvs(5.0F, 7.0F, 9.0F, 9.0F);
                                case SOUTH -> faceBuilder.uvs(5.0F, 5.0F, 9.0F, 7.0F);
                                case WEST -> faceBuilder.uvs(4.0F, 8.0F, 8.0F, 10.0F);
                                case UP -> faceBuilder.uvs(5.0F, 4.0F, 9.0F, 8.0F);
                                case DOWN -> faceBuilder.uvs(8.0F, 8.0F, 12.0F, 12.0F);
                            }
                        }).end()
                        .element().from(6.0F, 1.0F, 10.0F).to(10.0F, 2.0F, 15.0F)
                        .allFaces((direction, faceBuilder) -> {
                            faceBuilder.texture("#1");
                            switch (direction) {
                                case NORTH -> faceBuilder.uvs(1.0F, 5.0F, 5.0F, 6.0F);
                                case EAST -> faceBuilder.uvs(1.0F, 4.0F, 6.0F, 5.0F);
                                case SOUTH -> faceBuilder.uvs(8.0F, 11.0F, 12.0F, 12.0F);
                                case WEST -> faceBuilder.uvs(1.0F, 2.0F, 6.0F, 3.0F);
                                case UP -> faceBuilder.uvs(9.0F, 10.0F, 13.0F, 15.0F);
                                case DOWN -> faceBuilder.uvs(11.0F, 10.0F, 15.0F, 15.0F);
                            }
                        }).end()
                .transforms()
                        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                                .translation(0.0F, 1.5F, 0.0F).scale(0.2F, 0.2F, 0.2F).end()
                        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                                .translation(0.0F, 1.5F, 0.0F).scale(0.2F, 0.2F, 0.2F).end()
                        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                                .scale(0.5F, 0.5F, 0.5F).end()
                        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
                                .scale(0.5F, 0.5F, 0.5F).end()
                        .transform(ModelBuilder.Perspective.GROUND)
                                .translation(0.0F, 1.0F, 0.0F).scale(0.35F, 0.35F, 0.35F).end()
                        .transform(ModelBuilder.Perspective.GUI)
                                .rotation(30.0F, 45.0F, 0.0F).translation(0.0F, 0.75F, 0.0F).scale(0.6F, 0.6F, 0.6F).end()
                        .transform(ModelBuilder.Perspective.HEAD)
                                .translation(0.0F, 13.0F, 0.0F).end()
                        .transform(ModelBuilder.Perspective.FIXED)
                                .rotation(0.0F, 180.0F, 0.0F).scale(0.5F, 0.5F, 0.5F);

        soulMixierBottle("soul_mixier_bottle0",
                new float[]{12.0F, 2.0F, 1.0F, 12.0F, 11.0F, 6.0F},
                new float[]{5.0F, 14.0F, 0.0F, 5.0F, 0.0F, 14.0F, 5.0F, 5.0F});
        soulMixierBottle("soul_mixier_bottle1",
                new float[]{4.0F, 2.0F, 1.0F, 4.0F, 11.0F, 6.0F},
                new float[]{5.0F, 14.0F, 0.0F, 5.0F, 0.0F, 14.0F, 5.0F, 5.0F});
        soulMixierBottle("soul_mixier_bottle2",
                new float[]{8.0F, 2.0F, 10.0F, 8.0F, 11.0F, 15.0F},
                new float[]{5.0F, 14.0F, 0.0F, 5.0F, 0.0F, 14.0F, 5.0F, 5.0F});
        soulMixierBottle("soul_mixier_empty0",
                new float[]{12.0F, 2.0F, 1.0F, 12.0F, 11.0F, 6.0F},
                new float[]{15.0F, 15.0F, 16.0F, 16.0F, 15.0F, 15.0F, 16.0F, 16.0F});
        soulMixierBottle("soul_mixier_empty1",
                new float[]{4.0F, 2.0F, 1.0F, 4.0F, 11.0F, 6.0F},
                new float[]{15.0F, 15.0F, 16.0F, 16.0F, 15.0F, 15.0F, 16.0F, 16.0F});
        soulMixierBottle("soul_mixier_empty2",
                new float[]{8.0F, 2.0F, 10.0F, 8.0F, 11.0F, 15.0F},
                new float[]{15.0F, 15.0F, 16.0F, 16.0F, 15.0F, 15.0F, 16.0F, 16.0F});

    }
}
