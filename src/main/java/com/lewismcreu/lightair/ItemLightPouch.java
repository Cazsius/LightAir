package com.lewismcreu.lightair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLightPouch extends Item {
	public ItemLightPouch() {
		setUnlocalizedName("light_pouch");
		setRegistryName("light_pouch");
		setCreativeTab(Registry.LIGHTAIR);
	}

	private static int radius = 5;

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world,
			EntityPlayer player, EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		if (world.isRemote)
			return ActionResult.newResult(EnumActionResult.PASS, itemStack);

		BlockPos pos = player.getPosition();
		BlockPos minPos = pos.add(-radius, -radius, -radius);

		world.getMinecraftServer().addScheduledTask(() -> {
			for (int x = 0; x < (radius * 2 + 1); x++)
				for (int y = 0; y < (radius * 2 + 1); y++)
					for (int z = 0; z < (radius * 2 + 1); z++) {
						BlockPos n = minPos.add(x, y, z);
						if (world.isAirBlock(n))
							world.setBlockState(n, Registry.BLOCK_LIGHT_AIR
									.getStateFromMeta(15), 2);
					}
		});
		itemStack.shrink(1);
		return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
	}
}
