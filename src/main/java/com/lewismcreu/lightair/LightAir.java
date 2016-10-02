package com.lewismcreu.lightair;

import com.lewismcreu.lightair.proxy.CommonProxy;
import com.lewismcreu.lightair.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class LightAir
{
	// Mod Instance Declaration
	@Mod.Instance("LightAir")
	public static LightAir instance;

	// Sided Proxy Declaration
	@net.minecraftforge.fml.common.SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	// Pre Initialization events
	// Configuration, Items, Blocks
	@Mod.EventHandler
	public void preInit(net.minecraftforge.fml.common.event.FMLInitializationEvent event)
	{
		// Initialize CommonProxy
		CommonProxy.init();
	}

	// Initialization events
	@Mod.EventHandler
	public void init(net.minecraftforge.fml.common.event.FMLInitializationEvent event)
	{

	}

	// Post Initialization events
	@Mod.EventHandler
	public void postInit(net.minecraftforge.fml.common.event.FMLInitializationEvent event)
	{

	}
}
