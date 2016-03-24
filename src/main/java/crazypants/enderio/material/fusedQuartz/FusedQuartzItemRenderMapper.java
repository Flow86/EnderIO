package crazypants.enderio.material.fusedQuartz;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;

import org.apache.commons.lang3.tuple.Pair;

import crazypants.enderio.render.EnumMergingBlockRenderMode;
import crazypants.enderio.render.IBlockStateWrapper;
import crazypants.enderio.render.IOMode.EnumIOMode;
import crazypants.enderio.render.IRenderMapper;
import crazypants.enderio.render.pipeline.QuadCollector;

import static crazypants.enderio.render.EnumMergingBlockRenderMode.RENDER;

public class FusedQuartzItemRenderMapper implements IRenderMapper {

  public FusedQuartzItemRenderMapper() {
  }

  @Override
  public Pair<List<IBlockState>, List<IBakedModel>> mapItemRender(Block block, ItemStack stack) {
    List<IBlockState> states = new ArrayList<IBlockState>();
    IBlockState defaultState = block.getDefaultState();
    FusedQuartzType bankType = FusedQuartzType.getTypeFromMeta(stack.getItemDamage());
    defaultState = defaultState.withProperty(FusedQuartzType.KIND, bankType);

    states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.sides));

    for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, EnumFacing.UP)));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, EnumFacing.DOWN)));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, facing.rotateYCCW())));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, facing.rotateYCCW(), EnumFacing.UP)));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, facing.rotateYCCW(), EnumFacing.DOWN)));
    }
    return Pair.of(states, null);
  }

  @Override
  public Pair<List<IBlockState>, List<IBakedModel>> mapItemPaintOverlayRender(Block block, ItemStack stack) {
    return null;
  }

  @Override
  public List<IBlockState> mapBlockRender(IBlockStateWrapper state, IBlockAccess world, BlockPos pos, EnumWorldBlockLayer blockLayer,
      QuadCollector quadCollector) {
    return null;
  }

  @Override
  public EnumMap<EnumFacing, EnumIOMode> mapOverlayLayer(IBlockStateWrapper state, IBlockAccess world, BlockPos pos, boolean isPainted) {
    return null;
  }

}