package com.lothrazar.blocklayering.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLayering extends SnowlayerItem {

  Block parent = Blocks.SOUL_SAND;
  private int parentMeta;

  public ItemLayering(Block block, Block parent, int p) {
    
    super(block);
    Items.SNOW
    this.parent = parent;
    this.parentMeta = p;
  }

  @Override
  public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    EnumActionResult result = super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    IBlockState iblockstate = worldIn.getBlockState(pos);
    if (result == EnumActionResult.SUCCESS && iblockstate.getBlock() == this.block) {
      int i = iblockstate.getValue(BlockSnow.LAYERS).intValue();
      if (i == 8) {
        worldIn.setBlockState(pos, parent.getStateFromMeta(parentMeta));
      }
    }
    return result;
  }

  @Override
  public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
    IBlockState state = world.getBlockState(pos);
    return (state.getBlock() != this.block
        || (state.getValue(BlockSnow.LAYERS)) > 7) ? super.canPlaceBlockOnSide(world, pos, side, player, stack) : true;
  }
}
