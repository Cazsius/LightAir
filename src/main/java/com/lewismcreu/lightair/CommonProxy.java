package com.lewismcreu.lightair;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public static final BlockAirLight blockAirLight = new BlockAirLight();

	public void preInit()
	{
		GameRegistry.register(blockAirLight);
		GameRegistry.register(new ItemBlockLightAir(blockAirLight).setRegistryName(blockAirLight.getRegistryName()));
	}

	public void init()
	{}
}
