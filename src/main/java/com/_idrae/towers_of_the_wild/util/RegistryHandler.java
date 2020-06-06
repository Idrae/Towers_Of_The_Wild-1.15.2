package com._idrae.towers_of_the_wild.util;

import com._idrae.towers_of_the_wild.TowersOfTheWild;
import com._idrae.towers_of_the_wild.world.structures.TowerPieces;
import com._idrae.towers_of_the_wild.world.structures.TowerStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    // Structures
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, TowersOfTheWild.MOD_ID);
    public static final RegistryObject<Structure<NoFeatureConfig>> TOWER = registerStructure("tower", new TowerStructure(NoFeatureConfig::deserialize));

    private static <T extends Feature<?>> RegistryObject<T> registerStructure(String name, T feature) {
        TowersOfTheWild.LOGGER.info(name + " structure registered");
        return FEATURES.register(name, () -> feature);
    }

    // Structure Pieces
    public static final IStructurePieceType TOWER_PIECE = registerPiece("tower", TowerPieces.Piece::new);

    private static IStructurePieceType registerPiece(String key, IStructurePieceType type) {
        TowersOfTheWild.LOGGER.info(key + " structure piece registered");
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(TowersOfTheWild.MOD_ID, key), type);
    }
}
