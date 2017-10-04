package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBlockLightAir extends ItemBlock {
	public ItemBlockLightAir(BlockLightAir block)
	{
		super(block);
		this.setRegistryName(block.getRegistryName());
		hasSubtypes = true;
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote)
			return ActionResult.newResult(
					onItemUse(playerIn, worldIn, playerIn.getPosition()
							.offset(playerIn.getHorizontalFacing()).up(), hand,
							playerIn.getHorizontalFacing(), 0, 0, 0),
					playerIn.getHeldItem(hand));
		return super.onItemRightClick(worldIn, playerIn, hand);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (stack.getMetadata() > 0)
			return super.getUnlocalizedName(stack);
		return "tile.dark_air";
	}
}
