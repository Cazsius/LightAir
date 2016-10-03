package com.lewismcreu.lightair;

import net.minecraft.block.BlockAir;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAirLight extends BlockAir {

	public BlockAirLight()
	{
		super();
		this.setBlockName("blockAirLight");
		this.lightValue = 15;
		this.setCreativeTab(CreativeTabs.MISC);
	}



		
	private void setBlockName(String string) {
		
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
