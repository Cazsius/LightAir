package com.lewismcreu.lightair;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LightAir.MOD_ID, name = LightAir.MOD_NAME, version = LightAir.MOD_VERSION)
public class LightAir
{
	public static final String MOD_ID = "lightair";
	public static final String MOD_NAME = "Light Air";
	public static final String MOD_VERSION = "1.0";

	@Mod.Instance("lightair")
	public static LightAir instance;

	@SidedProxy(clientSide = "com.lewismcreu.lightair.ClientProxy", serverSide = "com.lewismcreu.lightair.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
		getUnlocalizedName("light_air");
		getRegistryName("light_air");
	}

	private void getRegistryName(String string) {
		
	}

	private void getUnlocalizedName(String string) {

		
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
}
