package com.lothrazar.blocklayering.registry;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.blocklayering.block.BlockLayering;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LayeringRegistry {

  public static List<BlockLayering> blocks = new ArrayList<BlockLayering>();
  private List<Block> blockBiomeColours = new ArrayList<>();

  public BlockLayering registerColour(BlockLayering b) {
    this.blockBiomeColours.add(b);
    return b;
  }

  //  public void registerModels(ModelRegistryEvent event) {
  //    String name;
  //    Item item;
  //    for (Block block : blocks) {
  //      item = Item.getItemFromBlock(block);
  //      name = ModBlockLayers.MODID + ":" + block.getUnlocalizedName().replaceAll("tile.", "");
  //      //      System.out.println(block.getUnlocalizedName() + ".name=");
  //      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
  //      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name));
  //    }
  //  } 
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public void registerBlockColors(ColorHandlerEvent.Block event) {
    BlockColors blockColors = event.getBlockColors();
    blockColors.register((state, worldIn, pos, tintIndex) -> {
      tintIndex = BiomeColors.getGrassColor(worldIn, pos);
      return tintIndex;
    }, this.blockBiomeColours.toArray(new Block[0]));
  }

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public void registerItemColors(ColorHandlerEvent.Item event) {
    List<Item> items = new ArrayList<>();
    for (Block b : this.blockBiomeColours) {
      items.add(Item.getItemFromBlock(b));
    }
    ItemColors blockColors = event.getItemColors();
    blockColors.register((stack, tintIndex) -> {
      tintIndex = 0x509026;
      return tintIndex;
    }, items.toArray(new Item[0]));
  }

  public BlockLayering createLayer(Block parent, String name) {
    BlockLayering block = new BlockLayering(parent, Block.Properties.create(parent.getMaterial(parent.getDefaultState())), "layer_" + name);
    blocks.add(block);
    return block;
  }
}
