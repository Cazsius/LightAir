package com.lewismcreu.lightair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		System.out.println("do this");
		super.init();
		Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(
				blockAirLight, new StateMap.Builder().ignore(BlockAirLight.LIGHT_LEVEL).build());
	}
}
