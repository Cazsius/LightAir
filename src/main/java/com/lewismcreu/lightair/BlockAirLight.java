package com.lewismcreu.lightair;

import net.minecraft.block.BlockAir;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAirLight extends BlockAir
{
	public BlockAirLight()
	{
		super();
		setUnlocalizedName("light_air");
		this.lightValue = 15;
		this.setCreativeTab(CreativeTabs.MISC);
	}

	public String getUnwrappedUnlocalizedName()
	{
		return getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
	}
}
