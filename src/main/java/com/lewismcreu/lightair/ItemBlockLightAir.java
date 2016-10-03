package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockLightAir extends ItemBlock
{
	public ItemBlockLightAir(BlockAirLight block)
	{
		super(block);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		EnumActionResult result = super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);

		if (result != EnumActionResult.FAIL)
		{
			System.out.println(stack.getItemDamage());
			placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, worldIn.getBlockState(pos)
					.withProperty(BlockAirLight.LIGHT_LEVEL, stack.getMetadata()));
		}

		return result;
	}
}
