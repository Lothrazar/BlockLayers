package com.lothrazar.blocklayering.registry;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.blocklayering.ModBlockLayers;
import com.lothrazar.blocklayering.block.BlockLayering;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ObjectHolder;

public class LayeringRegistry {

  @ObjectHolder(ModBlockLayers.MODID + ":layer_leaves_acacia")
  public static Block leaves_acacia;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_leaves_big_oak")
  public static Block leaves_dark;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_leaves_spruce")
  public static Block leaves_spruce;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_leaves_birch")
  public static Block leaves_birch;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_leaves_jungle")
  public static Block leaves_jun;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_leaves_oak")
  public static Block leaves_oak;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_grass")
  public static Block grass;
  @ObjectHolder(ModBlockLayers.MODID + ":layer_path")
  public static Block leaves_path;
  public static List<BlockLayering> blocks = new ArrayList<BlockLayering>();
  public static List<Block> blockBiomeColours = new ArrayList<>();

  public BlockLayering registerColour(BlockLayering b) {
    blockBiomeColours.add(b);
    return b;
  }

  public BlockLayering createLayer(Block parent, String name) {
    Properties props = Block.Properties.create(parent.getMaterial(parent.getDefaultState()));
    props.hardnessAndResistance(
        0.2F + parent.getBlockHardness(parent.getDefaultState(), null, null));
    props.sound(parent.getSoundType(parent.getDefaultState()));
    props.harvestLevel(0);
    props.harvestTool(ToolType.SHOVEL);
    BlockLayering block = new BlockLayering(parent, props, "layer_" + name);
    blocks.add(block);
    return block;
  }

  public BlockLayering createLeaves(Block parent, String name) {
    //    BlockLayering block = new BlockLayeringLeaves(parent, props, "layer_" + name);
    //    blocks.add(block);//doesnt work anyway
    return createLayer(parent, name);
  }
}
