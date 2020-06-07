package com.lothrazar.blocklayering.registry;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.blocklayering.block.BlockLayering;
import net.minecraft.block.Block;

public class LayeringRegistry {

  public static List<BlockLayering> blocks = new ArrayList<BlockLayering>();
  public static List<Block> blockBiomeColours = new ArrayList<>();

  public BlockLayering registerColour(BlockLayering b) {
    blockBiomeColours.add(b);
    return b;
  }

  public BlockLayering createLayer(Block parent, String name) {
    BlockLayering block = new BlockLayering(parent, Block.Properties.create(parent.getMaterial(parent.getDefaultState())), "layer_" + name);
    blocks.add(block);
    return block;
  }
}
