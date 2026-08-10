package com.lothrazar.blocklayering;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.library.block.BlockLayering;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class BlockLayeringFactory {

  public static List<BlockLayering> blocks = new ArrayList<BlockLayering>();
  public static List<Block> blockBiomes = new ArrayList<>();

  public BlockLayering register(BlockLayering b) {
    blockBiomes.add(b);
    return b;
  }

  public BlockLayering createLayer(Identifier id, Block parent) {
    return createLayer(id, parent, false);
  }

  @SuppressWarnings("deprecation")
  public BlockLayering createLayer(Identifier id, Block parent, boolean notsolid) {
    Block.Properties props = Block.Properties.of().setId(ResourceKey.create(Registries.BLOCK, id));

    props.strength(parent.defaultBlockState().destroySpeed);
    props.sound(parent.soundType); // .getSoundType(parent.defaultBlockState()) !!
    if (notsolid) {
      props.noOcclusion();
    }
    BlockLayering block = new BlockLayering(parent, props);
    blocks.add(block);
    return block;
  }
}
