package com.lothrazar.blocklayering;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.blocklayering.block.BlockLayering;
import com.lothrazar.blocklayering.registry.LayeringRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(ModBlockLayers.MODID) // "https://raw.githubusercontent.com/Lothrazar/DecoLayers/master/update.json")
public class ModBlockLayers {

  public static final String MODID = "blocklayering";
  public static Block icon = null;
  public static LayeringRegistry registry;
  public static CreativeModeTab tab = new CreativeModeTab(MODID) {

    @Override
    public ItemStack makeIcon() {
      return new ItemStack(icon);
    }
  };

  public ModBlockLayers() {
    registry = new LayeringRegistry();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
  }

  private void setup(final FMLClientSetupEvent event) {
    //    RenderType.getTranslucent()); 
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.grass, RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.leaves_oak, RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.leaves_acacia, RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.leaves_birch, RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.leaves_jun, RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.leaves_spruce, RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(LayeringRegistry.leaves_dark, RenderType.cutout());
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
        tintIndex = BiomeColors.getAverageGrassColor(worldIn, pos);
        return tintIndex;
      }, LayeringRegistry.blockBiomeColours.toArray(new Block[0]));
    }

    @SuppressWarnings("deprecation")
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
      List<Item> items = new ArrayList<>();
      for (Block b : LayeringRegistry.blockBiomeColours) {
        items.add(Item.byBlock(b));
      }
      ItemColors blockColors = event.getItemColors();
      blockColors.register((stack, tintIndex) -> {
        tintIndex = 0x509026;
        return tintIndex;
      }, items.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlocksRegistry(RegisterEvent event) {
      event.register(Registry.BLOCK_REGISTRY, r -> {
        r.register("layer_clay", registry.createLayer(Blocks.CLAY, Material.CLAY, "clay"));
        r.register("layer_sand", registry.createLayer(Blocks.SAND, Material.SAND, "sand"));
        r.register("layer_red_sand", registry.createLayer(Blocks.RED_SAND, Material.SAND, "red_sand"));
        r.register("layer_gravel", registry.createLayer(Blocks.GRAVEL, Material.SAND, "gravel"));
        BlockLayering hay = registry.createLayer(Blocks.HAY_BLOCK, Material.GRASS, "hay");
        r.register("layer_hay", hay);// for xisumavoid
        icon = hay;
        r.register("layer_soulsand", registry.createLayer(Blocks.SOUL_SAND, Material.SAND, "soulsand"));
        r.register("layer_dirt", registry.createLayer(Blocks.DIRT, Material.DIRT, "dirt"));
        r.register("layer_coarse_dirt", registry.createLayer(Blocks.COARSE_DIRT, Material.DIRT, "coarse_dirt"));
        r.register("layer_podzol", registry.createLayer(Blocks.PODZOL, Material.DIRT, "podzol"));
        r.register("layer_mycelium", registry.createLayer(Blocks.MYCELIUM, Material.DIRT, "mycelium"));
        LayeringRegistry.grass = registry.createLayer(Blocks.GRASS, Material.DIRT, "grass");
        r.register("layer_grass", registry.registerColour(LayeringRegistry.grass));
        r.register("layer_path", registry.createLayer(Blocks.DIRT_PATH, Material.DIRT, "path"));
        r.register("layer_concrete_powder_black", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_blue", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_brown", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_cyan", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_gray", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_green", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_light_blue", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_lime", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_magenta", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_orange", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_pink", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_purple", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_red", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_silver", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_white", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        r.register("layer_concrete_powder_yellow", registry.createLayer(Blocks.BLACK_CONCRETE_POWDER, Material.SAND, ""));
        LayeringRegistry.leaves_oak = registry.createLayer(Blocks.OAK_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_oak", registry.registerColour(LayeringRegistry.leaves_oak));
        LayeringRegistry.leaves_birch = registry.createLayer(Blocks.BIRCH_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_birch", registry.registerColour(LayeringRegistry.leaves_birch));
        LayeringRegistry.leaves_spruce = registry.createLayer(Blocks.SPRUCE_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_spruce", registry.registerColour(LayeringRegistry.leaves_spruce));
        LayeringRegistry.leaves_jun = registry.createLayer(Blocks.JUNGLE_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_jungle", registry.registerColour(LayeringRegistry.leaves_jun));
        LayeringRegistry.leaves_dark = registry.createLayer(Blocks.DARK_OAK_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_big_oak", registry.registerColour(LayeringRegistry.leaves_dark));
        LayeringRegistry.leaves_acacia = registry.createLayer(Blocks.ACACIA_LEAVES, Material.LEAVES, "", true);
        r.register("layer_leaves_acacia", registry.registerColour(LayeringRegistry.leaves_acacia));
      });
      Item.Properties properties = new Item.Properties().tab(ModBlockLayers.tab);
      event.register(Registry.ITEM_REGISTRY, reg -> {
        for (Block b : LayeringRegistry.blocks) {
          String id = b.getDescriptionId().replace(HAX, "");
          reg.register(id, new BlockItem(b, properties));
        }
      });
    }

    private static final String HAX = "block." + ModBlockLayers.MODID + ".";
  }
}
