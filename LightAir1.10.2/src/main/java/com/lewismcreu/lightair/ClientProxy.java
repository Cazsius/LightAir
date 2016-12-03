package com.lewismcreu.lightair;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	private static KeyBinding openGui;

	@Override
	public void init()
	{
		super.init();
		openGui = new KeyBinding("key.lightair.opengui", Keyboard.KEY_L,
				"key.category.lightair");
		ClientRegistry.registerKeyBinding(openGui);
		MinecraftForge.EVENT_BUS.register(this);
		Minecraft.getMinecraft().getBlockRendererDispatcher()
				.getBlockModelShapes().registerBlockWithStateMapper(
						blockLightAir, new StateMap.Builder()
								.ignore(BlockLightAir.LIGHT_LEVEL).build());

		for (int i = 0; i < 16; i++)
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
					.register(Item.getItemFromBlock(blockLightAir), i,
							new ModelResourceLocation(
									LightAir.MOD_ID + ":light_air",
									"inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(coalDust, 0, new ModelResourceLocation(
						LightAir.MOD_ID + ":coal_dust", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(coalBit, 0, new ModelResourceLocation(
						LightAir.MOD_ID + ":coal_bit", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(glowstoneBit, 0, new ModelResourceLocation(
						LightAir.MOD_ID + ":glowstone_bit", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(lightPouch, 0, new ModelResourceLocation(
						LightAir.MOD_ID + ":" + "light_pouch", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(darkBomb, 0, new ModelResourceLocation(
						LightAir.MOD_ID + ":" + "dark_bomb", "inventory"));
	}

	@SubscribeEvent
	public void onPlayerKeypressed(InputEvent.KeyInputEvent event)
	{
		if (openGui.isPressed()
				&& Minecraft.getMinecraft().player.capabilities.isCreativeMode)
			LightAir.channel.sendToServer(new OpenGuiMessage());
	}
}
