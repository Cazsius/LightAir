package com.lewismcreu.lightair;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockLightAir extends ItemBlock
{
	public ItemBlockLightAir(BlockAirLight block)
	{
		super(block);
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (stack.getMetadata() > 0) return super.getUnlocalizedName(stack);
		return "tile.dark_air";
	}
}
