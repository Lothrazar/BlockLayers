package com.lothrazar.blocklayering;

import java.util.List;
import com.lothrazar.library.block.BlockLayering;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(ModBlockLayers.MODID) // "https://raw.githubusercontent.com/Lothrazar/DecoLayers/master/update.json")
public class ModBlockLayers {

  public static final String MODID = "blocklayering";
  public static Block icon = null;
  public static BlockLayeringFactory factory;

  public ModBlockLayers() {
    factory = new BlockLayeringFactory();
  }

  @EventBusSubscriber(modid = ModBlockLayers.MODID) // , bus = EventBusSubscriber.Bus.MOD)
  public static class Registry {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
      BlockTintSource biomeGrassTint = new BlockTintSource() {
        @Override
        public int color(BlockState state) {
          return 0;
        }

        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
          if (pos == null || level == null) {
            return 0;
          }
          return BiomeColors.getAverageGrassColor(level, pos);
        }
      };
      event.register(List.of(biomeGrassTint), BlockLayeringFactory.blockBiomes.toArray(new Block[0]));
    }

    // item icon tinting for these same blocks (formerly RegisterColorHandlersEvent.Item, now
    // fully data-driven) is applied via assets/blocklayering/items/<name>.json "tints" instead

    @SubscribeEvent
    public static void onBlocksRegistry(RegisterEvent event) {
      event.register(Registries.BLOCK, r -> {
        Identifier idClay = Identifier.fromNamespaceAndPath(MODID, "layer_clay");
        r.register(idClay, factory.createLayer(idClay, Blocks.CLAY));
        Identifier idSand = Identifier.fromNamespaceAndPath(MODID, "layer_sand");
        r.register(idSand, factory.createLayer(idSand, Blocks.SAND));
        Identifier idRedSand = Identifier.fromNamespaceAndPath(MODID, "layer_red_sand");
        r.register(idRedSand, factory.createLayer(idRedSand, Blocks.RED_SAND));
        Identifier idGravel = Identifier.fromNamespaceAndPath(MODID, "layer_gravel");
        r.register(idGravel, factory.createLayer(idGravel, Blocks.GRAVEL));
        Identifier idHay = Identifier.fromNamespaceAndPath(MODID, "layer_hay");
        BlockLayering hay = factory.createLayer(idHay, Blocks.HAY_BLOCK);
        r.register(idHay, hay);// for xisumavoid
        icon = hay;
        Identifier idSoulsand = Identifier.fromNamespaceAndPath(MODID, "layer_soulsand");
        r.register(idSoulsand, factory.createLayer(idSoulsand, Blocks.SOUL_SAND));
        Identifier idDirt = Identifier.fromNamespaceAndPath(MODID, "layer_dirt");
        r.register(idDirt, factory.createLayer(idDirt, Blocks.DIRT));
        Identifier idCoarseDirt = Identifier.fromNamespaceAndPath(MODID, "layer_coarse_dirt");
        r.register(idCoarseDirt, factory.createLayer(idCoarseDirt, Blocks.COARSE_DIRT));
        Identifier idPodzol = Identifier.fromNamespaceAndPath(MODID, "layer_podzol");
        r.register(idPodzol, factory.createLayer(idPodzol, Blocks.PODZOL));
        Identifier idMycelium = Identifier.fromNamespaceAndPath(MODID, "layer_mycelium");
        r.register(idMycelium, factory.createLayer(idMycelium, Blocks.MYCELIUM));
        Identifier idGrass = Identifier.fromNamespaceAndPath(MODID, "layer_grass");
        r.register(idGrass, factory.register(factory.createLayer(idGrass, Blocks.GRASS_BLOCK)));
        Identifier idPath = Identifier.fromNamespaceAndPath(MODID, "layer_path");
        r.register(idPath, factory.createLayer(idPath, Blocks.DIRT_PATH));
        Identifier idConcreteBlack = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_black");
        r.register(idConcreteBlack, factory.createLayer(idConcreteBlack, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteBlue = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_blue");
        r.register(idConcreteBlue, factory.createLayer(idConcreteBlue, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteBrown = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_brown");
        r.register(idConcreteBrown, factory.createLayer(idConcreteBrown, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteCyan = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_cyan");
        r.register(idConcreteCyan, factory.createLayer(idConcreteCyan, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteGray = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_gray");
        r.register(idConcreteGray, factory.createLayer(idConcreteGray, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteGreen = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_green");
        r.register(idConcreteGreen, factory.createLayer(idConcreteGreen, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteLightBlue = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_light_blue");
        r.register(idConcreteLightBlue, factory.createLayer(idConcreteLightBlue, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteLime = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_lime");
        r.register(idConcreteLime, factory.createLayer(idConcreteLime, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteMagenta = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_magenta");
        r.register(idConcreteMagenta, factory.createLayer(idConcreteMagenta, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteOrange = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_orange");
        r.register(idConcreteOrange, factory.createLayer(idConcreteOrange, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcretePink = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_pink");
        r.register(idConcretePink, factory.createLayer(idConcretePink, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcretePurple = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_purple");
        r.register(idConcretePurple, factory.createLayer(idConcretePurple, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteRed = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_red");
        r.register(idConcreteRed, factory.createLayer(idConcreteRed, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteSilver = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_silver");
        r.register(idConcreteSilver, factory.createLayer(idConcreteSilver, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteWhite = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_white");
        r.register(idConcreteWhite, factory.createLayer(idConcreteWhite, Blocks.BLACK_CONCRETE_POWDER));
        Identifier idConcreteYellow = Identifier.fromNamespaceAndPath(MODID, "layer_concrete_powder_yellow");
        r.register(idConcreteYellow, factory.createLayer(idConcreteYellow, Blocks.BLACK_CONCRETE_POWDER));
        //leaves with biome filter
        Identifier idMud = Identifier.fromNamespaceAndPath(MODID, "layer_mud");
        r.register(idMud, factory.createLayer(idMud, Blocks.MUD, true));
        Identifier idPackedMud = Identifier.fromNamespaceAndPath(MODID, "layer_packed_mud");
        r.register(idPackedMud, factory.createLayer(idPackedMud, Blocks.PACKED_MUD, true));
        Identifier idLeavesOak = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_oak");
        r.register(idLeavesOak, factory.register(factory.createLayer(idLeavesOak, Blocks.OAK_LEAVES, true)));
        Identifier idLeavesBirch = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_birch");
        r.register(idLeavesBirch, factory.register(factory.createLayer(idLeavesBirch, Blocks.BIRCH_LEAVES, true)));
        Identifier idLeavesSpruce = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_spruce");
        r.register(idLeavesSpruce, factory.register(factory.createLayer(idLeavesSpruce, Blocks.SPRUCE_LEAVES, true)));
        Identifier idLeavesJungle = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_jungle");
        r.register(idLeavesJungle, factory.register(factory.createLayer(idLeavesJungle, Blocks.JUNGLE_LEAVES, true)));
        Identifier idLeavesBigOak = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_big_oak");
        r.register(idLeavesBigOak, factory.register(factory.createLayer(idLeavesBigOak, Blocks.DARK_OAK_LEAVES, true)));
        Identifier idLeavesAcacia = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_acacia");
        r.register(idLeavesAcacia, factory.register(factory.createLayer(idLeavesAcacia, Blocks.ACACIA_LEAVES, true)));
        //new
        Identifier idLeavesMangrove = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_mangrove");
        r.register(idLeavesMangrove, factory.register(factory.createLayer(idLeavesMangrove, Blocks.MANGROVE_LEAVES, true)));
        Identifier idLeavesCherry = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_cherry");
        r.register(idLeavesCherry, factory.createLayer(idLeavesCherry, Blocks.CHERRY_LEAVES, true));
        Identifier idLeavesAzalea = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_azalea");
        r.register(idLeavesAzalea, factory.register(factory.createLayer(idLeavesAzalea, Blocks.AZALEA_LEAVES, true)));
        Identifier idLeavesFloweringAzalea = Identifier.fromNamespaceAndPath(MODID, "layer_leaves_flowering_azalea");
        r.register(idLeavesFloweringAzalea, factory.createLayer(idLeavesFloweringAzalea, Blocks.FLOWERING_AZALEA_LEAVES, true));
        //
      });
      event.register(Registries.ITEM, reg -> {
        for (Block b : BlockLayeringFactory.blocks) {
          String id = b.getDescriptionId().replace(HAX, "");
          Identifier itemId = Identifier.fromNamespaceAndPath(MODID, id);
          Item.Properties properties = new Item.Properties()
              .setId(ResourceKey.create(Registries.ITEM, itemId))
              .useBlockDescriptionPrefix();
          reg.register(itemId, new BlockItem(b, properties));
        }
      });
    }

    private static final String HAX = "block." + ModBlockLayers.MODID + ".";
    private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(ModBlockLayers.MODID, "tab"));

    @SubscribeEvent
    public static void onCreativeModeTabRegister(RegisterEvent event) {
      event.register(Registries.CREATIVE_MODE_TAB, helper -> {
        helper.register(TAB, CreativeModeTab.builder().icon(() -> new ItemStack(icon))
            .title(Component.translatable("itemGroup." + ModBlockLayers.MODID))
            .displayItems((enabledFlags, populator) -> {
              for (Block b : BlockLayeringFactory.blocks) {
                populator.accept(b);
              }
            }).build());
      });
      //      event.registerCreativeModeTab(Identifier.fromNamespaceAndPath(ModBlockLayers.MODID, "tab"), builder -> builder
      //          .title(Component.translatable("itemGroup." + ModBlockLayers.MODID))
      //          .icon(() -> new ItemStack(icon))
      //          .displayItems((enabledFlags, populator) -> {
      //            for (Block b : BlockLayeringFactory.blocks) {
      //              populator.accept(new ItemStack(b));
      //            }
      //          }));
      //    RegistryFactory.buildTab(event, ModAbsentBD.MODID, new ItemStack(FENCE_QUARTZ.asItem()), null);
    }
  }
}
