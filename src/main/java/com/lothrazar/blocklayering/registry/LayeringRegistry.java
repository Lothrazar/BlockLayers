package com.lothrazar.blocklayering.registry;

import com.lothrazar.blocklayering.block.BlockLayering;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class LayeringRegistry {

  public static BlockLayering leaves_acacia;
  public static BlockLayering leaves_dark;
  public static BlockLayering leaves_spruce;
  public static BlockLayering leaves_birch;
  public static BlockLayering leaves_jun;
  public static BlockLayering leaves_oak;
  public static BlockLayering grass; 
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
    BlockLayering block = new BlockLayering(parent, props);
    blocks.add(block);
    return block;
  }
}
