package com.lothrazar.blocklayering;

import org.apache.logging.log4j.Logger;
import com.lothrazar.blocklayering.registry.LayeringRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ModBlockLayers.MODID)
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
    registry.createLayer(Material.ROCK, Items.CLAY_BALL, "clay");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SAND), "sand");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SAND), "red_sand");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.GRAVEL), "gravel");
    registry.createLayer(Material.ROCK, Items.WHEAT, "hay");// for xisumavoid
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "soulsand");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "dirt");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "coarse_dirt");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "podzol");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "mycelium");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "grass");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "path");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_black");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_blue");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_brown");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_cyan");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_gray");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_green");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_light_blue");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_lime");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_magenta");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_orange");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_pink");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_purple");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_red");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_silver");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_white");
    registry.createLayer(Material.ROCK, Item.getItemFromBlock(Blocks.SOUL_SAND), "concrete_powder_yellow");
    // grass_path
    //snow layers: sand, red_sand, gravel, soulsand, clay, ?dirt
 
  }
}
