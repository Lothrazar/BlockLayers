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

@Mod(modid = ModAbsentBD.MODID)
public class ModAbsentBD {

  @GameRegistry.ObjectHolder(ModAbsentBD.MODID + ":hay")
  public static final Block icon = null;
  @Instance(ModAbsentBD.MODID)
  public static ModAbsentBD instance;
  public static final CreativeTabs tab = new CreativeTabs(ModAbsentBD.MODID) {

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return new ItemStack(icon);
    }
  };
  public static final String MODID = "blocklayering";
  private static Logger logger;
  private LayeringRegistry registry;

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
    ////////layer_clay 
    //STAIRS ONLY: Snow 
    // SLAB ONLY:  chiseled brick, grass_path
    //snow layers: sand, red_sand, gravel, soulsand, clay, ?dirt
    // IRON BARS: gold, obsidian
    // Trapdoor: 6x wood plank
  }
}
