package com.lewismcreu.lightair.proxy;

import com.lewismcreu.lightair.BlockAirLight;

public abstract class CommonProxy implements IProxy
{
	public static final BlockAirLight blockAirLight = new BlockAirLight();
	
	@SuppressWarnings("deprecation")
	public static void init()
	{
		net.minecraftforge.fml.common.registry.GameRegistry.registerBlock(blockAirLight, "blockAirLight");
	}
}
