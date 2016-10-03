package com.lewismcreu.lightair;

import net.minecraft.item.ItemBlock;

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
}
