package com.cowate.cuprumethyst.Data.client;

import com.cowate.cuprumethyst.Cuprumethyst;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Cuprumethyst.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("raw_cuprumethyst_block", modLoc("block/raw_cuprumethyst_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "amethyst_dust");
        builder(itemGenerated, "oxidized_copper_ingot");
        builder(itemGenerated, "raw_cuprumethyst_ingot");
        builder(itemGenerated, "soul_powder");
        builder(itemGenerated, "soul_rod");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
