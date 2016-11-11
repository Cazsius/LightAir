package com.lewismcreu.lightair;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBlockLightAir extends ItemBlock
{
	public ItemBlockLightAir(BlockAirLight block)
	{
		super(block);
		hasSubtypes = true;
	}
	
	public ItemBlockLightAir(Block block)
	{
		super(block);
		hasSubtypes = true;
	}


	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn,
			World worldIn, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ)
	{
		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY,
				hitZ);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) 
	{
			if (!worldIn.isRemote)
			{
				onItemUse(itemStackIn, playerIn, worldIn, playerIn.getPosition().offset(playerIn.getHorizontalFacing())
					.up(), playerIn.getHorizontalFacing(),0, 0, 0);
				return itemStackIn;
			}
			return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		if (stack.getMetadata() > 0) tooltip.add(I18n.format("lightair.tooltip.lightlevel", stack
		.getMetadata()));
			 }

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (stack.getMetadata() > 0) return super.getUnlocalizedName(stack);
		return "tile.dark_air";
	}
}
