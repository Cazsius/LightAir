package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ItemLightPouch extends Item
{
	public ItemLightPouch()
	{
		setUnlocalizedName("light_pouch");
		setRegistryName("light_pouch");
		setCreativeTab(CommonProxy.LIGHTAIR);
	}

	private static int radius = 5;

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world,
			EntityPlayer player)
	{
		if (world.isRemote) return itemStack;

		BlockPos pos = player.getPosition();
		BlockPos minPos = pos.add(-radius, -radius, -radius);

		FMLCommonHandler.instance().getMinecraftServerInstance()
				.addScheduledTask(() -> {
					for (int x = 0; x < (radius * 2 + 1); x++)
						for (int y = 0; y < (radius * 2 + 1); y++)
							for (int z = 0; z < (radius * 2 + 1); z++)
							{
								BlockPos n = minPos.add(x, y, z);
								if (world
										.isAirBlock(n))
									world.setBlockState(n,
											CommonProxy.blockLightAir
													.getStateFromMeta(15),
											2);
							}
				});
		itemStack.stackSize--;
		return itemStack;
	}
}
