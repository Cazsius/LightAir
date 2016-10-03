package com.lewismcreu.lightair;

import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy
{
	public static final BlockAirLight blockAirLight = new BlockAirLight();

	public void preInit()
	{
		GameRegistry.register(blockAirLight);
	}
}
