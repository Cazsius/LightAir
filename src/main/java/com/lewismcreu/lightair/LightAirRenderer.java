package com.lewismcreu.lightair;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import com.lewismcreu.lightair.proxy.CommonProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class LightAirRenderer implements ISimpleBlockRenderingHandler
{
	public static int renderId;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		if (block == CommonProxy.blockAirLight)
		{
			if (EventHandler.toggleVis)
			{
				renderer.renderBlockByRenderType(Blocks.glowstone, x, y, z);
			}
		}

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return this.renderId;
	}
}
