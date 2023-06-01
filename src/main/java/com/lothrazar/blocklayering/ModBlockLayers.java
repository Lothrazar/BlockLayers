package com.lothrazar.blocklayering;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.library.block.BlockLayering;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod(ModBlockLayers.MODID) // "https://raw.githubusercontent.com/Lothrazar/DecoLayers/master/update.json")
public class ModBlockLayers {

  public static final String MODID = "blocklayering";
  public static Block icon = null;
  public static BlockLayeringFactory factory;

  public ModBlockLayers() {
    factory = new BlockLayeringFactory();
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class Registry {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
      event.register((state, worldIn, pos, tintIndex) -> {
        if (pos == null || worldIn == null) {
          return 0;
        }
        tintIndex = BiomeColors.getAverageGrassColor(worldIn, pos);
        return tintIndex;
      }, BlockLayeringFactory.blockBiomeColours.toArray(new Block[0]));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
      List<Item> items = new ArrayList<>();
      for (Block b : BlockLayeringFactory.blockBiomeColours) {
        items.add(b.asItem());
      }
      //      ItemColors blockColors = event.getItemColors();
      event.register((stack, tintIndex) -> {
        tintIndex = 0x509026;
        return tintIndex;
      }, items.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlocksRegistry(RegisterEvent event) {
      event.register(Registries.BLOCK, r -> {
        r.register("layer_clay", factory.createLayer(Blocks.CLAY, Material.CLAY, "clay"));
        r.register("layer_sand", factory.createLayer(Blocks.SAND, Material.SAND, "sand"));
        r.register("layer_red_sand", factory.createLayer(Blocks.RED_SAND, Material.SAND, "red_sand"));
        r.register("layer_gravel", factory.createLayer(Blocks.GRAVEL, Material.SAND, "gravel"));
        BlockLayering hay = factory.createLayer(Blocks.HAY_BLOCK, Material.GRASS, "hay");
        r.register("layer_hay", hay);// for xisumavoid
        icon = hay;
        r.register("layer_soulsand", factory.createLayer(Blocks.SOUL_SAND, Material.SAND, "soulsand"));
        r.register("layer_dirt", factory.createLayer(Blocks.DIRT, Material.DIRT, "dirt"));
        r.register("layer_coarse_dirt", factory.createLayer(Blocks.COARSE_DIRT, Material.DIRT, "coarse_dirt"));
        r.register("layer_podzol", factory.createLayer(Blocks.PODZOL, Material.DIRT, "podzol"));
        r.register("layer_mycelium", factory.createLayer(Blocks.MYCELIUM, Material.DIRT, "mycelium"));
        r.register("layer_grass", factory.registerColour(factory.createLayer(Blocks.GRASS, Material.DIRT, "grass")));
        r.register("layer_path", factory.createLayer(Blocks.DIRT_PATH, Material.DIRT, "path"));
        r.register("layer_concrete_powder_black", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_blue", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_brown", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_cyan", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_gray", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_green", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_light_blue", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_lime", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_magenta", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_orange", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_pink", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_purple", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_red", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_silver", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_white", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_yellow", factory.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        BlockLayering leaves_oak = factory.createLayer(Blocks.OAK_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_oak", factory.registerColour(leaves_oak));
        BlockLayering leaves_birch = factory.createLayer(Blocks.BIRCH_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_birch", factory.registerColour(leaves_birch));
        BlockLayering leaves_spruce = factory.createLayer(Blocks.SPRUCE_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_spruce", factory.registerColour(leaves_spruce));
        BlockLayering leaves_jun = factory.createLayer(Blocks.JUNGLE_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_jungle", factory.registerColour(leaves_jun));
        BlockLayering leaves_dark = factory.createLayer(Blocks.DARK_OAK_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_big_oak", factory.registerColour(leaves_dark));
        BlockLayering leaves_acacia = factory.createLayer(Blocks.ACACIA_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_acacia", factory.registerColour(leaves_acacia));
      });
      Item.Properties properties = new Item.Properties();
      event.register(Registries.ITEM, reg -> {
        for (Block b : BlockLayeringFactory.blocks) {
          String id = b.getDescriptionId().replace(HAX, "");
          reg.register(id, new BlockItem(b, properties));
        }
      });
    }

    private static final String HAX = "block." + ModBlockLayers.MODID + ".";

    @SubscribeEvent
    public static void buildContents(CreativeModeTabEvent.Register event) {
      event.registerCreativeModeTab(new ResourceLocation(ModBlockLayers.MODID, "tab"), builder -> builder
          .title(Component.translatable("itemGroup." + ModBlockLayers.MODID))
          .icon(() -> new ItemStack(icon))
          .displayItems((enabledFlags, populator) -> {
            for (Block b : BlockLayeringFactory.blocks) {
              populator.accept(new ItemStack(b));
            }
          }));
      //    RegistryFactory.buildTab(event, ModAbsentBD.MODID, new ItemStack(FENCE_QUARTZ.asItem()), null);
    }
  }
}
