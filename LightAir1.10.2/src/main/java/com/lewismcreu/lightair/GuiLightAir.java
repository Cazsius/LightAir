package com.lewismcreu.lightair;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLightAir extends GuiContainer
{
	private static final ResourceLocation background = new ResourceLocation(LightAir.MOD_ID,
			"textures/gui/light_air.png");
	private static final int arrowX = 125, topArrowY = 11, topArrowHoverX = arrowX + 61, topArrowHoverY = topArrowY - 1,
			botArrowY = topArrowY + 50, botArrowHoverX = arrowX + 60, botArrowHoverY = botArrowY + 1, arrowWidth = 22,
			arrowHeight = 14;

	public GuiLightAir(ContainerLightAir inventorySlotsIn)
	{
		super(inventorySlotsIn);
		xSize = 176;
		ySize = 207;
		guiLeft = (Minecraft.getMinecraft().displayWidth - xSize) / 2;
		guiTop = (Minecraft.getMinecraft().displayHeight - ySize) / 2;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		int x = 136;
		int y = 40;
		mc.getTextureManager().bindTexture(background);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if (isPointInRegion(arrowX, topArrowY, arrowWidth, arrowHeight, mouseX, mouseY)) drawTexturedModalRect(guiLeft
				+ arrowX, guiTop + topArrowY, topArrowHoverX, topArrowHoverY, arrowWidth, arrowHeight);
		else if (isPointInRegion(arrowX, botArrowY, arrowWidth, arrowHeight, mouseX, mouseY)) drawTexturedModalRect(
				guiLeft + arrowX, guiTop + botArrowY, botArrowHoverX, botArrowHoverY, arrowWidth, arrowHeight);
		drawCenteredString(fontRendererObj, "§l" + ((ContainerLightAir) inventorySlots).getItemMeta() + "", guiLeft + x,
				guiTop + y, Color.WHITE.getRGB());
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		if (mouseButton == 0)
		{
			if (isPointInRegion(arrowX, topArrowY, arrowWidth, arrowHeight, mouseX, mouseY))
			{
				mc.playerController.sendEnchantPacket(this.inventorySlots.windowId, 0);
				((ContainerLightAir) inventorySlots).enchantItem(mc.player, 0);
				return;
			}
			else if (isPointInRegion(arrowX, botArrowY, arrowWidth, arrowHeight, mouseX, mouseY))
			{
				mc.playerController.sendEnchantPacket(this.inventorySlots.windowId, 1);
				((ContainerLightAir) inventorySlots).enchantItem(mc.player, 1);
				return;
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}
