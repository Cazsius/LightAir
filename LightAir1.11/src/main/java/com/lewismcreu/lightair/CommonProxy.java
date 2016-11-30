package com.lewismcreu.lightair;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy
{
	public static final CreativeTabs LIGHTAIR = new CreativeTabs("lightair")
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(blockAirLight));
		}
	};

	public static final BlockAirLight blockAirLight = new BlockAirLight();
	public static final Item coalDust = new Item()
			.setUnlocalizedName("coal_dust").setRegistryName("coal_dust")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item coalBit = new Item().setUnlocalizedName("coal_bit")
			.setRegistryName("coal_bit").setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item glowstoneBit =
			new Item().setUnlocalizedName("glowstone_bit")
					.setRegistryName("glowstone_bit")
					.setCreativeTab(CreativeTabs.MATERIALS);

	public void preInit()
	{
		GameRegistry.register(blockAirLight);
		GameRegistry.register(new ItemBlockLightAir(blockAirLight)
				.setRegistryName(blockAirLight.getRegistryName()));
		GameRegistry.register(coalDust);
		GameRegistry.register(coalBit);
		GameRegistry.register(glowstoneBit);
	}

	public void init()
	{
		OreDictionary.registerOre("dustCoal", coalDust);
		OreDictionary.registerOre("coal_dust", coalDust);

		OreDictionary.registerOre("bitGlowstone", glowstoneBit);
		OreDictionary.registerOre("glowstone_bit", glowstoneBit);

		OreDictionary.registerOre("bitCoal", coalBit);
		OreDictionary.registerOre("coal_bit", coalBit);

		GameRegistry.addShapelessRecipe(new ItemStack(coalDust, 4),
				new ItemStack(Items.COAL));
		GameRegistry.addShapelessRecipe(new ItemStack(coalBit, 4),
				new ItemStack(coalDust));
		GameRegistry.addShapelessRecipe(new ItemStack(glowstoneBit, 4),
				new ItemStack(Items.GLOWSTONE_DUST));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2,
				new ItemStack[] { new ItemStack(coalDust),
						new ItemStack(coalDust), new ItemStack(coalDust),
						new ItemStack(coalDust) },
				new ItemStack(Items.COAL)));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2,
				new ItemStack[] { new ItemStack(coalBit),
						new ItemStack(coalBit), new ItemStack(coalBit),
						new ItemStack(coalBit) },
				new ItemStack(coalDust)));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2, new ItemStack[] {
				new ItemStack(glowstoneBit), new ItemStack(glowstoneBit),
				new ItemStack(glowstoneBit), new ItemStack(glowstoneBit) },
				new ItemStack(Items.GLOWSTONE_DUST)));
		GameRegistry.addShapelessRecipe(new ItemStack(blockAirLight),
				new ItemStack(coalDust), new ItemStack(Items.GLOWSTONE_DUST));
		GameRegistry.addRecipe(new IRecipe()
		{
			@Override
			public boolean matches(InventoryCrafting inv, World worldIn)
			{
				List<ItemStack> input = new ArrayList<>();

				for (int i = 0; i < inv.getSizeInventory(); i++)
					if (inv.getStackInSlot(i) != null)
						input.add(inv.getStackInSlot(i));

				if (input.size() < 2) return false;

				int counter = 0;
				for (ItemStack i : input)
				{
					if (!isValidInput(i)) return false;
					if (i.getItem() == Item.getItemFromBlock(blockAirLight))
					{
						counter++;
						if (counter > 1) return false;
					}
				}
				if (counter != 1) return false;

				return true;
			}

			private boolean isValidInput(ItemStack in)
			{
				return in != null
						? in.getItem() == Item.getItemFromBlock(blockAirLight)
								|| OreDictionary.itemMatches(
										new ItemStack(Items.GLOWSTONE_DUST), in,
										false)
								|| OreDictionary.itemMatches(
										new ItemStack(coalDust), in, false)
								|| OreDictionary.itemMatches(
										new ItemStack(coalBit), in, false)
								|| OreDictionary.itemMatches(
										new ItemStack(glowstoneBit), in, false)
						: false;
			}

			@Override
			public ItemStack getCraftingResult(InventoryCrafting inv)
			{
				if (!matches(inv, null)) return null;
				ItemStack out =
						new ItemStack(Item.getItemFromBlock(blockAirLight));
				int meta = 0;
				int lighter = 0;
				for (int i = 0; i < inv.getSizeInventory(); i++)
				{
					ItemStack in = inv.getStackInSlot(i);
					if (in != null)
					{
						if (in.getItem() == Item
								.getItemFromBlock(blockAirLight))
							meta = in.getItemDamage();
						else if (OreDictionary.itemMatches(
								new ItemStack(Items.GLOWSTONE_DUST), in, false))
							lighter += 4;
						else if (OreDictionary.itemMatches(
								new ItemStack(coalDust), in, false))
							lighter -= 4;
						else if (OreDictionary.itemMatches(
								new ItemStack(coalBit), in, false))
							lighter--;
						else if (OreDictionary.itemMatches(
								new ItemStack(glowstoneBit), in, false))
							lighter++;
					}
				}
				meta += lighter;
				meta = MathHelper.clamp_int(meta, 0, 15);
				out.setItemDamage(meta);
				return out;
			}

			@Override
			public int getRecipeSize()
			{
				return 2;
			}

			@Override
			public ItemStack getRecipeOutput()
			{
				return new ItemStack(Item.getItemFromBlock(blockAirLight));
			}

			@Override
			public NonNullList<ItemStack> getRemainingItems(
					InventoryCrafting inv)
			{
				return NonNullList.create();
				// Old implementation: didn't work!
				// ItemStack[] out = new ItemStack[inv.getSizeInventory()];
				// for (int i = 0; i < inv.getSizeInventory(); i++)
				// {
				// out[i] = inv.getStackInSlot(i) == null ? null : inv.getStackInSlot(i).copy();
				// if (out[i] != null)
				// {
				// out[i].stackSize--;
				// if (out[i].stackSize <= 0) out[i] = null;
				// }
				// }
				// return out;
			}
		});
	}
}
