package com.lewismcreu.lightair;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public static final BlockAirLight blockAirLight = new BlockAirLight();

	public void preInit()
	{
		GameRegistry.register(blockAirLight);
		GameRegistry.register(new ItemBlock(blockAirLight).setRegistryName(blockAirLight.getRegistryName()));
	}

	public void init()
	{}
}
