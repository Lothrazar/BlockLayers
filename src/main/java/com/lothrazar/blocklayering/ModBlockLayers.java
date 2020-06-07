package com.lothrazar.blocklayering;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.blocklayering.block.BlockLayering;
import com.lothrazar.blocklayering.registry.LayeringRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod(ModBlockLayers.MODID) // "https://raw.githubusercontent.com/Lothrazar/DecoLayers/master/update.json")
public class ModBlockLayers {

  public static final String MODID = "blocklayering";
  @ObjectHolder(ModBlockLayers.MODID + ":layer_clay")
  public static final Block icon = null;
  public static LayeringRegistry registry;
  public static final Logger LOGGER = LogManager.getLogger();
  public static ItemGroup tab = new ItemGroup(MODID) {

    @Override
    public ItemStack createIcon() {
      return new ItemStack(icon);
    }
  };

  public ModBlockLayers() {
    MinecraftForge.EVENT_BUS.register(this);
    registry = new LayeringRegistry();
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {

    @SubscribeEvent
    public static void onBlocksRegistry(RegistryEvent.Register<Block> event) {
      IForgeRegistry<Block> r = event.getRegistry();
      r.register(ModBlockLayers.registry.createLayer(Blocks.CLAY, "clay"));
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
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.BLUE.getMetadata(), "concrete_powder_blue");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.BROWN.getMetadata(), "concrete_powder_brown");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.CYAN.getMetadata(), "concrete_powder_cyan");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.GRAY.getMetadata(), "concrete_powder_gray");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.GREEN.getMetadata(), "concrete_powder_green");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.LIGHT_BLUE.getMetadata(), "concrete_powder_light_blue");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.LIME.getMetadata(), "concrete_powder_lime");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.MAGENTA.getMetadata(), "concrete_powder_magenta");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.ORANGE.getMetadata(), "concrete_powder_orange");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.PINK.getMetadata(), "concrete_powder_pink");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.PURPLE.getMetadata(), "concrete_powder_purple");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.RED.getMetadata(), "concrete_powder_red");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.SILVER.getMetadata(), "concrete_powder_silver");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.WHITE.getMetadata(), "concrete_powder_white");
      //      registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.YELLOW.getMetadata(), "concrete_powder_yellow");
      // grass_path
      //snow layers: sand, red_sand, gravel, soulsand, clay, ?dirt
      //      registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.OAK.getMetadata(), "leaves_oak")).setCutout();
      //      registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.BIRCH.getMetadata(), "leaves_birch")).setCutout();
      //      registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.SPRUCE.getMetadata(), "leaves_spruce")).setCutout();
      //      registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.JUNGLE.getMetadata(), "leaves_jungle")).setCutout();
      //      registry.registerColour(registry.createLayer(Blocks.LEAVES2, BlockPlanks.EnumType.DARK_OAK.getMetadata(), "leaves_big_oak")).setCutout();
      //      registry.registerColour(registry.createLayer(Blocks.LEAVES2, BlockPlanks.EnumType.ACACIA.getMetadata(), "leaves_acacia")).setCutout();
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

  private void setup(FMLCommonSetupEvent event) {}
}
