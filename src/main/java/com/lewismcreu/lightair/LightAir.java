package com.lewismcreu.lightair;

import com.lewismcreu.lightair.proxy.CommonProxy;
import com.lewismcreu.lightair.proxy.IProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.MOD_VERSION)
public class LightAir
{
	// Mod Instance Declaration
	@Mod.Instance("LightAir")
	public static LightAir instance;
	
	// Sided Proxy Declaration
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	// Pre Initialization events
	// Configuration, Items, Blocks
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Initialize CommonProxy
		CommonProxy.init();
	}
	
	// Initialization events
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	// Post Initialization events
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	 
}
