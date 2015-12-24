package com.lewismcreu.lightair;

<<<<<<< HEAD
import net.minecraft.block.BlockAir;
import net.minecraft.creativetab.CreativeTabs;
=======
import java.util.Random;
>>>>>>> origin/master

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAirLight extends Block
{
	public BlockAirLight()
	{
<<<<<<< HEAD
		super();
		this.setBlockName("blockAirLight");
		this.lightValue = 15;
		this.setCreativeTab(CreativeTabs.tabMisc);
=======
		super(Material.air);
		this.setTickRandomly(true);
		this.setBlockName("blockAirLight");
		this.lightValue = 15;
		this.setCreativeTab(CreativeTabs.tabBlock);
>>>>>>> origin/master
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
<<<<<<< HEAD
=======
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
			int y, int z)
	{

		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		System.out.println("Display Particles");
		if (EventHandler.toggleVis)
		{
			double d0 = (double) ((float) x + 0.5F);
			double d1 = (double) ((float) y + 1.2F);
			double d2 = (double) ((float) z + 0.5F);

			world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public int getRenderType()
	{
		return LightAirRenderer.renderId;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_)
	{
		return false;
>>>>>>> origin/master
	}
}
