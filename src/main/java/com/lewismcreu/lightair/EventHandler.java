package com.lewismcreu.lightair;

import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class EventHandler
{
	public static boolean toggleVis = false;

	private static Key getPressedKey()
	{
		if (Keybindings.togglevis.isPressed())
		{
			return Key.TOGGLEVIS;
		} else
		{
			return Key.UNKNOWN;
		}
	}

	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event)
	{
		Key k = getPressedKey();

		if (k == Key.TOGGLEVIS)
		{
			if (toggleVis)
			{
				toggleVis = false;
			} else
			{
				toggleVis = true;
			}
		}
	}

	public enum Key
	{
		UNKNOWN, TOGGLEVIS;
	}
	
	@SubscribeEvent
	public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
	{
		
	}
}
