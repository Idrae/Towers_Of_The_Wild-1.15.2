package com._idrae.towers_of_the_wild.config;

import com._idrae.towers_of_the_wild.TowersOfTheWild;
import com.google.common.collect.Lists;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Mod.EventBusSubscriber(modid = TowersOfTheWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TowersOfTheWildConfig {

    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    public static int rarity;
    public static List<String> biomeBlackList;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(TowersOfTheWildConfig.CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == TowersOfTheWildConfig.COMMON_SPEC) {
            bakeConfig();
            TowersOfTheWild.LOGGER.info("Config set");
        }
    }

    public static void bakeConfig() {
        rarity = COMMON.towerRarity.get();
        biomeBlackList = COMMON.biomeBlackList.get();
        TowersOfTheWild.LOGGER.info(rarity);
    }

    public static class CommonConfig {

        public final ForgeConfigSpec.IntValue towerRarity;
        public final ForgeConfigSpec.ConfigValue<List<String>> biomeBlackList;

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("towers");
            towerRarity = builder
                    .comment("How rarely the towers will spawn (low: common, high: rare). Default: 35")
                    .defineInRange("towerRarity", 35, 10, 1000);

            biomeBlackList = builder
                    .comment("A list of biomes where the towers do not spawn. Default: Oceans, Rivers, Beaches, Nether, End.")
                    .define("biomeBlackList",
                            Lists.newArrayList(
                                "minecraft:ocean",
                                "minecraft:deep_ocean",
                                "minecraft:frozen_ocean",
                                "minecraft:deep_frozen_ocean",
                                "minecraft:cold_ocean",
                                "minecraft:deep_cold_ocean",
                                "minecraft:lukewarm_ocean",
                                "minecraft:deep_lukewarm_ocean",
                                "minecraft:warm_ocean",
                                "minecraft:deep_warm_ocean",
                                "minecraft:river",
                                "minecraft:frozen_river",
                                "minecraft:beach",
                                "minecraft:stone_shore",
                                "minecraft:snowy_beach",
                                "minecraft:nether",
                                "minecraft:the_end",
                                "minecraft:small_end_islands",
                                "minecraft:end_midlands",
                                "minecraft:end_highlands",
                                "minecraft:end_barrens",
                                "minecraft:the_void",
                                "biomesoplenty:gravel_beach",
                                "biomesoplenty:white_beach",
                                "biomesoplenty:ashen_inferno",
                                "biomesoplenty:undergarden",
                                "biomesoplenty:visceral_heap"
                    ));

            builder.pop();
        }
    }
}
