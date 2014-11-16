package com.lewismcreu.lightair.proxy;

import com.lewismcreu.lightair.Keybindings;
import com.lewismcreu.lightair.LightAirRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public static void init()
	{
		setCustomRenderers();
	}
	
	private static void setCustomRenderers()
	{
		LightAirRenderer.renderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(LightAirRenderer.renderId, new LightAirRenderer());
	}

	public void registerKeyBindings()
	{
		ClientRegistry.registerKeyBinding(Keybindings.togglevis);
	}
	
}
