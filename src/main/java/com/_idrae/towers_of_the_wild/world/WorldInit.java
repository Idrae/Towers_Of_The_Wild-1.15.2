package com._idrae.towers_of_the_wild.world;

import com._idrae.towers_of_the_wild.TowersOfTheWild;
import com._idrae.towers_of_the_wild.config.TowersOfTheWildConfig;
import com._idrae.towers_of_the_wild.util.RegistryHandler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IglooPieces;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldInit {

    public static void setup() {
        for (Biome biome : ForgeRegistries.BIOMES) {

            if (!TowersOfTheWildConfig.biomeBlackList.contains(biome.getRegistryName().toString())) {
                // TowersOfTheWild.LOGGER.debug("biome added");
                // TowersOfTheWild.LOGGER.debug(biome.getRegistryName().toString());
                addSurfaceStructure(biome, RegistryHandler.TOWER.get());
            }
        }
    }

    private static void addSurfaceStructure(Biome biome, Structure<NoFeatureConfig> structure) {
        biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }
}
