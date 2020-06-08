package com.lothrazar.blocklayering;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.blocklayering.block.BlockLayering;
import com.lothrazar.blocklayering.registry.LayeringRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod(ModBlockLayers.MODID) // "https://raw.githubusercontent.com/Lothrazar/DecoLayers/master/update.json")
public class ModBlockLayers {

  public static final String MODID = "blocklayering";
  @ObjectHolder(ModBlockLayers.MODID + ":layer_hay")
  public static final Block icon = null;
  public static LayeringRegistry registry;
  public static ItemGroup tab = new ItemGroup(MODID) {

    @Override
    public ItemStack createIcon() {
      return new ItemStack(icon);
    }
  };

  public ModBlockLayers() {
    registry = new LayeringRegistry();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
  }

  private void setup(final FMLClientSetupEvent event) {
    RenderTypeLookup.setRenderLayer(LayeringRegistry.grass, RenderType.getCutout());
    //    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_path, RenderType.getTranslucent());
    //cutout fucked up doesnt work lol
    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_oak, RenderType.getSolid());
    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_acacia, RenderType.getSolid());
    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_birch, RenderType.getSolid());
    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_jun, RenderType.getSolid());
    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_spruce, RenderType.getSolid());
    RenderTypeLookup.setRenderLayer(LayeringRegistry.leaves_dark, RenderType.getSolid());
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
      BlockColors blockColors = event.getBlockColors();
      blockColors.register((state, worldIn, pos, tintIndex) -> {
        if (pos == null || worldIn == null) {
          return 0;
        }
        tintIndex = BiomeColors.getGrassColor(worldIn, pos);
        return tintIndex;
      }, LayeringRegistry.blockBiomeColours.toArray(new Block[0]));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
      List<Item> items = new ArrayList<>();
      for (Block b : LayeringRegistry.blockBiomeColours) {
        items.add(Item.getItemFromBlock(b));
      }
      ItemColors blockColors = event.getItemColors();
      blockColors.register((stack, tintIndex) -> {
        tintIndex = 0x509026;
        return tintIndex;
      }, items.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlocksRegistry(RegistryEvent.Register<Block> event) {
      IForgeRegistry<Block> r = event.getRegistry();
      r.register(registry.createLayer(Blocks.CLAY, "clay"));
      r.register(registry.createLayer(Blocks.SAND, "sand"));
      r.register(registry.createLayer(Blocks.RED_SAND, "red_sand"));
      r.register(registry.createLayer(Blocks.GRAVEL, "gravel"));
      r.register(registry.createLayer(Blocks.HAY_BLOCK, "hay"));// for xisumavoid
      r.register(registry.createLayer(Blocks.SOUL_SAND, "soulsand"));
      r.register(registry.createLayer(Blocks.DIRT, "dirt"));
      r.register(registry.createLayer(Blocks.COARSE_DIRT, "coarse_dirt"));
      r.register(registry.createLayer(Blocks.PODZOL, "podzol"));
      r.register(registry.createLayer(Blocks.MYCELIUM, "mycelium"));
      r.register(registry.registerColour(registry.createLayer(Blocks.GRASS, "grass")));
      r.register(registry.createLayer(Blocks.GRASS_PATH, "path"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_black"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_blue"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_brown"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_cyan"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_gray"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_green"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_light_blue"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_lime"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_magenta"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_orange"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_pink"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_purple"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_red"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_silver"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_white"));
      r.register(registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, "concrete_powder_yellow"));
      r.register(registry.registerColour(registry.createLeaves(Blocks.OAK_LEAVES, "leaves_oak")));
      r.register(registry.registerColour(registry.createLeaves(Blocks.BIRCH_LEAVES, "leaves_birch")));
      r.register(registry.registerColour(registry.createLeaves(Blocks.SPRUCE_LEAVES, "leaves_spruce")));
      r.register(registry.registerColour(registry.createLeaves(Blocks.JUNGLE_LEAVES, "leaves_jungle")));
      r.register(registry.registerColour(registry.createLeaves(Blocks.DARK_OAK_LEAVES, "leaves_big_oak")));
      r.register(registry.registerColour(registry.createLeaves(Blocks.ACACIA_LEAVES, "leaves_acacia")));
    }

    @SubscribeEvent
    public static void onItemsRegistry(RegistryEvent.Register<Item> event) {
      IForgeRegistry<Item> r = event.getRegistry();
      Item.Properties properties = new Item.Properties().group(ModBlockLayers.tab);
      for (BlockLayering b : LayeringRegistry.blocks) {
        r.register(new BlockItem(b, properties).setRegistryName(b.rawName()));
      }
    }
  }
}
