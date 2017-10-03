package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerLightAir extends Container
{
	private final ItemStack itemStack;

	public ContainerLightAir(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}

	@Override
	public boolean enchantItem(EntityPlayer playerIn, int id)
	{
		if (id != 0 && id != 1) return super.enchantItem(playerIn, id);

		int meta = getItemMeta();
		if (id == 0)
		{
			if (meta == 15) return false;
			meta++;
		}
		else if (id == 1)
		{
			if (meta == 0) return false;
			meta--;
		}
		setItemMeta(meta);
		return true;
	}

	public int getItemMeta()
	{
		return itemStack.getMetadata();
	}

	public void setItemMeta(int meta)
	{
		itemStack.setItemDamage(meta);
	}
}
