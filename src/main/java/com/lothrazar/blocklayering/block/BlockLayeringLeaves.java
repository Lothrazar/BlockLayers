package com.lothrazar.blocklayering.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockLayeringLeaves extends BlockLayering {

  public BlockLayeringLeaves(Block main, Properties props, String reg) {
    super(main, props, reg);
  }
  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
//LeavesBlock x;
  }

  public boolean isSolid(BlockState state) {
     return false;
  }

  @Override
  public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return 1;
  }

  @Override
  public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return false;
  }
}
