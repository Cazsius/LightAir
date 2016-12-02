package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDarkBomb extends Item
{
	public ItemDarkBomb()
	{
		setUnlocalizedName("dark_bomb");
		setRegistryName("dark_bomb");
		setCreativeTab(CommonProxy.LIGHTAIR);
	}

	private static int cubeSize = 17;

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack,
			World world, EntityPlayer player, EnumHand hand)
	{
		if (world.isRemote)
			return ActionResult.newResult(EnumActionResult.PASS, itemStack);

		BlockPos pos = player.getPosition();
		BlockPos minPos = pos.add(-cubeSize / 2, -cubeSize / 2, -cubeSize / 2);

		world.getMinecraftServer().addScheduledTask(() -> {
			for (int x = 0; x < cubeSize; x++)
				for (int y = 0; y < cubeSize; y++)
					for (int z = 0; z < cubeSize; z++)
					{
						BlockPos n = minPos.add(x, y, z);
						if (world.getBlockState(n)
								.getBlock() instanceof BlockLightAir)
							world.setBlockToAir(n);
					}
		});
		itemStack.stackSize--;
		return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
	}
}
