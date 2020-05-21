package com.lothrazar.blocklayering;

import org.apache.logging.log4j.Logger;
import com.lothrazar.blocklayering.registry.LayeringRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ModBlockLayers.MODID, updateJSON = "https://raw.githubusercontent.com/Lothrazar/DecoLayers/trunk/1.12/update.json")
public class ModBlockLayers {

  public static final String MODID = "blocklayering";
  @GameRegistry.ObjectHolder(ModBlockLayers.MODID + ":layer_hay")
  public static final Block icon = null;
  @Instance(ModBlockLayers.MODID)
  public static ModBlockLayers instance;
  private static Logger logger;
  private LayeringRegistry registry;
  public static final CreativeTabs tab = new CreativeTabs(ModBlockLayers.MODID) {

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return new ItemStack(icon);
    }
  };

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    registry = new LayeringRegistry();
    MinecraftForge.EVENT_BUS.register(registry);
    //now register content 
    registry.createLayer(Blocks.CLAY, "clay");
    registry.createLayer(Blocks.SAND, "sand");
    registry.createLayer(Blocks.SAND, 1, "red_sand");
    registry.createLayer(Blocks.GRAVEL, "gravel");
    registry.createLayer(Blocks.HAY_BLOCK, "hay");// for xisumavoid
    registry.createLayer(Blocks.SOUL_SAND, "soulsand");
    registry.createLayer(Blocks.DIRT, "dirt");
    registry.createLayer(Blocks.DIRT, 1, "coarse_dirt");
    registry.createLayer(Blocks.DIRT, 2, "podzol");
    registry.createLayer(Blocks.MYCELIUM, "mycelium");
    registry.registerColour(registry.createLayer(Blocks.GRASS, "grass"));
    registry.createLayer(Blocks.GRASS_PATH, "path");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.BLACK.getMetadata(), "concrete_powder_black");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.BLUE.getMetadata(), "concrete_powder_blue");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.BROWN.getMetadata(), "concrete_powder_brown");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.CYAN.getMetadata(), "concrete_powder_cyan");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.GRAY.getMetadata(), "concrete_powder_gray");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.GREEN.getMetadata(), "concrete_powder_green");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.LIGHT_BLUE.getMetadata(), "concrete_powder_light_blue");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.LIME.getMetadata(), "concrete_powder_lime");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.MAGENTA.getMetadata(), "concrete_powder_magenta");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.ORANGE.getMetadata(), "concrete_powder_orange");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.PINK.getMetadata(), "concrete_powder_pink");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.PURPLE.getMetadata(), "concrete_powder_purple");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.RED.getMetadata(), "concrete_powder_red");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.SILVER.getMetadata(), "concrete_powder_silver");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.WHITE.getMetadata(), "concrete_powder_white");
    registry.createLayer(Blocks.CONCRETE_POWDER, EnumDyeColor.YELLOW.getMetadata(), "concrete_powder_yellow");
    // grass_path
    //snow layers: sand, red_sand, gravel, soulsand, clay, ?dirt
    registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.OAK.getMetadata(), "leaves_oak")).setCutout();
    registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.BIRCH.getMetadata(), "leaves_birch")).setCutout();
    registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.SPRUCE.getMetadata(), "leaves_spruce")).setCutout();
    registry.registerColour(registry.createLayer(Blocks.LEAVES, BlockPlanks.EnumType.JUNGLE.getMetadata(), "leaves_jungle")).setCutout();
    registry.registerColour(registry.createLayer(Blocks.LEAVES2, BlockPlanks.EnumType.DARK_OAK.getMetadata(), "leaves_big_oak")).setCutout();
    registry.registerColour(registry.createLayer(Blocks.LEAVES2, BlockPlanks.EnumType.ACACIA.getMetadata(), "leaves_acacia")).setCutout();
  }
}
