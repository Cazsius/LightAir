package com.lewismcreu.lightair;

import java.util.List;

import net.minecraft.block.BlockAir;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BlockAirLight extends BlockAir
{
	public static final PropertyInteger LIGHT_LEVEL = PropertyInteger.create("lightlevel", 0, 15);

	public BlockAirLight()
	{
		super();
		setUnlocalizedName("light_air");
		setRegistryName("light_air");
		setCreativeTab(CreativeTabs.MISC);
		setDefaultState(getDefaultState().withProperty(LIGHT_LEVEL, 15));
		setResistance(6000001.0F);
		disableStats();
		translucent = true;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer)
	{
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(LIGHT_LEVEL,
				meta);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		if (stack.getMetadata() > 0) tooltip.add(I18n.translateToLocalFormatted("lightair.tooltip.lightlevel", stack
				.getMetadata()));
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < 16; i++)
		{
			ItemStack is = new ItemStack(itemIn);
			is.setItemDamage(i);
			list.add(is);
		}
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

	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}
}
