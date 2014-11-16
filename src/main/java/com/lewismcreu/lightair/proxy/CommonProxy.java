package com.lewismcreu.lightair.proxy;

import com.lewismcreu.lightair.BlockAirLight;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
	public static final BlockAirLight blockAirLight = new BlockAirLight();
	
	public static void init()
	{
		GameRegistry.registerBlock(blockAirLight, "blockAirLight");
	}
}
