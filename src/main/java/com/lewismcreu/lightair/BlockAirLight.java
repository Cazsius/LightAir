package com.lewismcreu.lightair;

import net.minecraft.block.BlockAir;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAirLight extends BlockAir
{
	public static final PropertyInteger LIGHT_LEVEL = PropertyInteger.create("lightlevel", 0, 15);

	public BlockAirLight()
	{
		super();
		setUnlocalizedName("light_air");
		setRegistryName("light_air");
		this.setCreativeTab(CreativeTabs.MISC);
		setDefaultState(getDefaultState().withProperty(LIGHT_LEVEL, 15));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LIGHT_LEVEL);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(LIGHT_LEVEL);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(LIGHT_LEVEL, meta);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(LIGHT_LEVEL);
	}

	public String getUnwrappedUnlocalizedName()
	{
		return getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
	}
}
