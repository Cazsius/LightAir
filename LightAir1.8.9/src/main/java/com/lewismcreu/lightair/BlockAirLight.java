package com.lewismcreu.lightair;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockAir;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class BlockAirLight extends BlockAir
{
	public static final PropertyInteger LIGHT_LEVEL =
			PropertyInteger.create("lightlevel", 0, 15);

	public BlockAirLight()
	{
		super();
		setUnlocalizedName("light_air");
		setRegistryName("light_air");
		setCreativeTab(CommonProxy.LIGHTAIR);
		setDefaultState(getDefaultState().withProperty(LIGHT_LEVEL, 15));
		setResistance(6000001.0F);
		disableStats();
		translucent = true;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer)
	{
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta,
				placer).withProperty(LIGHT_LEVEL, meta);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab,
			List<ItemStack> list)
	{
		for (int i = 0; i < 16; i++)
		{
			ItemStack is = new ItemStack(itemIn);
			is.setItemDamage(i);
			list.add(is);
		}
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, LIGHT_LEVEL);
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
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		return world.getBlockState(pos).getValue(LIGHT_LEVEL);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos,
			IBlockState state, Random rand)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		if (p.getHeldItem() != null && p.getHeldItem().getItem() == Item
				.getItemFromBlock(CommonProxy.blockAirLight))
		{
			for (int i = 0; i < 2; i++)
				worldIn.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + 0.5,
						pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0.00001, 0);
		}
	}
}
