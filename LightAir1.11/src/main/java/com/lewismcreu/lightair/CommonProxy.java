package com.lewismcreu.lightair;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Lewis_McReu
 */
public class CommonProxy
{
	public static final CreativeTabs LIGHTAIR = new CreativeTabs("lightair")
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(BLOCK_LIGHT_AIR));
		}
	};

	public static final BlockLightAir BLOCK_LIGHT_AIR = new BlockLightAir();
	public static final BlockStructuralAir BLOCK_STRUCTURAL_AIR =
			new BlockStructuralAir();
	public static final Item ITEM_COAL_DUST = new Item()
			.setUnlocalizedName("coal_dust").setRegistryName("coal_dust")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ITEM_COAL_BIT = new Item()
			.setUnlocalizedName("coal_bit").setRegistryName("coal_bit")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ITEM_GLOWSTONE_BIT =
			new Item().setUnlocalizedName("glowstone_bit")
					.setRegistryName("glowstone_bit")
					.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item ITEM_LIGHT_BOMB = new ItemLightPouch();
	public static final Item ITEM_DARK_BOMB = new ItemDarkBomb();
	private static final ItemBlockLightAir ITEM_LIGHT_AIR =
			new ItemBlockLightAir(BLOCK_LIGHT_AIR);

	public void preInit()
	{
		GameRegistry.register(BLOCK_LIGHT_AIR);
		ITEM_LIGHT_AIR.register();
		GameRegistry.register(BLOCK_STRUCTURAL_AIR);
		GameRegistry.register(new ItemBlock(BLOCK_STRUCTURAL_AIR)
				.setRegistryName(BLOCK_STRUCTURAL_AIR.getRegistryName()));
		GameRegistry.register(ITEM_COAL_DUST);
		GameRegistry.register(ITEM_COAL_BIT);
		GameRegistry.register(ITEM_GLOWSTONE_BIT);
		GameRegistry.register(ITEM_LIGHT_BOMB);
		GameRegistry.register(ITEM_DARK_BOMB);
	}

	public void init()
	{
		OreDictionary.registerOre("dustCoal", ITEM_COAL_DUST);
		OreDictionary.registerOre("coal_dust", ITEM_COAL_DUST);

		OreDictionary.registerOre("bitGlowstone", ITEM_GLOWSTONE_BIT);
		OreDictionary.registerOre("glowstone_bit", ITEM_GLOWSTONE_BIT);

		OreDictionary.registerOre("bitCoal", ITEM_COAL_BIT);
		OreDictionary.registerOre("coal_bit", ITEM_COAL_BIT);

		GameRegistry.addShapelessRecipe(new ItemStack(ITEM_COAL_DUST, 4),
				new ItemStack(Items.COAL));
		GameRegistry.addShapelessRecipe(new ItemStack(ITEM_COAL_BIT, 4),
				new ItemStack(ITEM_COAL_DUST));
		GameRegistry.addShapelessRecipe(new ItemStack(ITEM_GLOWSTONE_BIT, 4),
				new ItemStack(Items.GLOWSTONE_DUST));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2, new ItemStack[] {
				new ItemStack(ITEM_COAL_DUST), new ItemStack(ITEM_COAL_DUST),
				new ItemStack(ITEM_COAL_DUST), new ItemStack(ITEM_COAL_DUST) },
				new ItemStack(Items.COAL)));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2, new ItemStack[] {
				new ItemStack(ITEM_COAL_BIT), new ItemStack(ITEM_COAL_BIT),
				new ItemStack(ITEM_COAL_BIT), new ItemStack(ITEM_COAL_BIT) },
				new ItemStack(ITEM_COAL_DUST)));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2,
				new ItemStack[] { new ItemStack(ITEM_GLOWSTONE_BIT),
						new ItemStack(ITEM_GLOWSTONE_BIT),
						new ItemStack(ITEM_GLOWSTONE_BIT),
						new ItemStack(ITEM_GLOWSTONE_BIT) },
				new ItemStack(Items.GLOWSTONE_DUST)));
		GameRegistry.addShapelessRecipe(new ItemStack(BLOCK_LIGHT_AIR),
				new ItemStack(ITEM_COAL_DUST),
				new ItemStack(Items.GLOWSTONE_DUST));
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
					if (i.getItem() == Item.getItemFromBlock(BLOCK_LIGHT_AIR))
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
						? in.getItem() == Item.getItemFromBlock(BLOCK_LIGHT_AIR)
								|| OreDictionary.itemMatches(
										new ItemStack(Items.GLOWSTONE_DUST), in,
										false)
								|| OreDictionary.itemMatches(
										new ItemStack(ITEM_COAL_DUST), in,
										false)
								|| OreDictionary.itemMatches(
										new ItemStack(ITEM_COAL_BIT), in, false)
								|| OreDictionary.itemMatches(
										new ItemStack(ITEM_GLOWSTONE_BIT), in,
										false)
						: false;
			}

			@Override
			public ItemStack getCraftingResult(InventoryCrafting inv)
			{
				if (!matches(inv, null)) return null;
				ItemStack out =
						new ItemStack(Item.getItemFromBlock(BLOCK_LIGHT_AIR));
				int meta = 0;
				int lighter = 0;
				for (int i = 0; i < inv.getSizeInventory(); i++)
				{
					ItemStack in = inv.getStackInSlot(i);
					if (in != null)
					{
						if (in.getItem() == Item
								.getItemFromBlock(BLOCK_LIGHT_AIR))
							meta = in.getItemDamage();
						else if (OreDictionary.itemMatches(
								new ItemStack(Items.GLOWSTONE_DUST), in, false))
							lighter += 4;
						else if (OreDictionary.itemMatches(
								new ItemStack(ITEM_COAL_DUST), in, false))
							lighter -= 4;
						else if (OreDictionary.itemMatches(
								new ItemStack(ITEM_COAL_BIT), in, false))
							lighter--;
						else if (OreDictionary.itemMatches(
								new ItemStack(ITEM_GLOWSTONE_BIT), in, false))
							lighter++;
					}
				}
				meta += lighter;
				meta = MathHelper.clamp(meta, 0, 15);
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
				return new ItemStack(Item.getItemFromBlock(BLOCK_LIGHT_AIR));
			}

			@Override
			public NonNullList<ItemStack> getRemainingItems(
					InventoryCrafting inv)
			{
				return NonNullList.create();
			}
		});
	}
}
