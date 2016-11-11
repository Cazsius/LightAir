package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

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

	public static SimpleNetworkWrapper channel;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
		channel = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		channel.registerMessage(OpenGuiMessage.Handler.class, OpenGuiMessage.class, 0, Side.SERVER);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	public class GuiHandler implements IGuiHandler
	{
		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
		{
			if (ID == 0)
			{
				ItemStack s = player.getHeldItemMainhand();
				if (s != null && s.getItem() == Item.getItemFromBlock(CommonProxy.blockAirLight))
					return new ContainerLightAir(s);
			}
			return null;
		}

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
		{
			if (ID == 0)
			{
				ItemStack s = player.getHeldItemMainhand();
				if (s != null && s.getItem() == Item.getItemFromBlock(CommonProxy.blockAirLight))
					return new GuiLightAir(new ContainerLightAir(s));
			}
			return null;
		}
	}
}
