package com.lothrazar.blocklayering.block;

import javax.annotation.Nullable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;

public class BlockLayering extends Block {

  public static final IntegerProperty LAYERS = SnowLayerBlock.LAYERS;
  protected static final VoxelShape[] SHAPES = new VoxelShape[] { Shapes.empty(), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
      Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
      Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
      Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };
  private String rawName;

  public BlockLayering(Block parent, Block.Properties props, String reg) {
    super(props);
    this.rawName = reg;
    this.setRegistryName(reg);
    this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(LAYERS);
  }

  public String rawName() {
    return rawName;
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return SHAPES[state.getValue(LAYERS)];
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return SHAPES[state.getValue(LAYERS) - 1];
  }

  @Override
  public boolean useShapeForLightOcclusion(BlockState state) {
    return true;
  }

  @SuppressWarnings("deprecation")
  @Override
  public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
    return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
  }

  @Override
  public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
    int i = state.getValue(LAYERS);
    if (useContext.getItemInHand().getItem() == this.asItem() && i < 8) {
      if (useContext.replacingClickedOnBlock()) {
        return useContext.getClickedFace() == Direction.UP;
      }
      else {
        return true;
      }
    }
    else {
      return i == 1;
    }
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
    if (blockstate.getBlock() == this) {
      int i = blockstate.getValue(LAYERS);
      return blockstate.setValue(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
    }
    else {
      return super.getStateForPlacement(context);
    }
  }
}
