package com.lothrazar.blocklayering.registry;

import com.lothrazar.blocklayering.ModBlockLayers;
import com.lothrazar.blocklayering.block.BlockLayering;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
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

  public BlockLayering createLayer(Block parent, Material mat, String name) {
    return createLayer(parent, mat, name, false);
  }

  @SuppressWarnings("deprecation")
  public BlockLayering createLayer(Block parent, Material mat, String name, boolean notsolid) {
    Block.Properties props = Block.Properties.of(mat);
    props.strength(parent.defaultBlockState().destroySpeed);
    props.sound(parent.getSoundType(parent.defaultBlockState()));
    if (notsolid) {
      props.noOcclusion();
    }
    BlockLayering block = new BlockLayering(parent, props, "layer_" + name);
    blocks.add(block);
    return block;
  }
}
