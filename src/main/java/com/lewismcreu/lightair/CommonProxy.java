package com.lewismcreu.lightair;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy
{
	public static final CreativeTabs LIGHTAIR = new CreativeTabs("lightair")
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(BLOCK_LIGHT_AIR);
		}
	};

	public static final BlockLightAir BLOCK_LIGHT_AIR = new BlockLightAir();
	public static final BlockStructuralAir BLOCK_STRUCTURAL_AIR =
			new BlockStructuralAir();
	public static final Item COAL_DUST = new Item()
			.setUnlocalizedName("coal_dust").setRegistryName("coal_dust")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COAL_BIT = new Item()
			.setUnlocalizedName("coal_bit").setRegistryName("coal_bit")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GLOWSTONE_BIT =
			new Item().setUnlocalizedName("glowstone_bit")
					.setRegistryName("glowstone_bit")
					.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item LIGHT_POUCH = new ItemLightPouch();
	public static final Item DARK_BOMB = new ItemDarkBomb();

	public void preInit()
	{
		GameRegistry.register(BLOCK_LIGHT_AIR);
		GameRegistry.register(new ItemBlockLightAir(BLOCK_LIGHT_AIR),
				BLOCK_LIGHT_AIR.getRegistryName());
		GameRegistry.register(BLOCK_STRUCTURAL_AIR);
		GameRegistry.register(new ItemBlock(BLOCK_STRUCTURAL_AIR),
				BLOCK_STRUCTURAL_AIR.getRegistryName());
		GameRegistry.register(COAL_DUST);
		GameRegistry.register(COAL_BIT);
		GameRegistry.register(GLOWSTONE_BIT);
		GameRegistry.register(LIGHT_POUCH);
		GameRegistry.register(DARK_BOMB);
	}

	public void init()
	{
		OreDictionary.registerOre("dustCoal", COAL_DUST);
		OreDictionary.registerOre("coal_dust", COAL_DUST);

		OreDictionary.registerOre("bitGlowstone", GLOWSTONE_BIT);
		OreDictionary.registerOre("glowstone_bit", GLOWSTONE_BIT);

		OreDictionary.registerOre("bitCoal", COAL_BIT);
		OreDictionary.registerOre("coal_bit", COAL_BIT);

		GameRegistry.addShapelessRecipe(new ItemStack(COAL_DUST, 4),
				new ItemStack(Items.COAL));
		GameRegistry.addShapelessRecipe(new ItemStack(COAL_BIT, 4),
				new ItemStack(COAL_DUST));
		GameRegistry.addShapelessRecipe(new ItemStack(GLOWSTONE_BIT, 4),
				new ItemStack(Items.GLOWSTONE_DUST));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2,
				new ItemStack[] { new ItemStack(COAL_DUST),
						new ItemStack(COAL_DUST), new ItemStack(COAL_DUST),
						new ItemStack(COAL_DUST) },
				new ItemStack(Items.COAL)));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2,
				new ItemStack[] { new ItemStack(COAL_BIT),
						new ItemStack(COAL_BIT), new ItemStack(COAL_BIT),
						new ItemStack(COAL_BIT) },
				new ItemStack(COAL_DUST)));
		GameRegistry.addRecipe(new ShapedRecipes(2, 2, new ItemStack[] {
				new ItemStack(GLOWSTONE_BIT), new ItemStack(GLOWSTONE_BIT),
				new ItemStack(GLOWSTONE_BIT), new ItemStack(GLOWSTONE_BIT) },
				new ItemStack(Items.GLOWSTONE_DUST)));
		GameRegistry.addShapelessRecipe(new ItemStack(BLOCK_LIGHT_AIR),
				new ItemStack(COAL_DUST), new ItemStack(Items.GLOWSTONE_DUST));
		GameRegistry.addRecipe(new ShapedRecipes(3, 3, new ItemStack[] {
				new ItemStack(BLOCK_LIGHT_AIR, 1, 15), null,
				new ItemStack(BLOCK_LIGHT_AIR, 1, 15),
				new ItemStack(Items.LEATHER),
				new ItemStack(BLOCK_LIGHT_AIR, 1, 15),
				new ItemStack(Items.LEATHER), new ItemStack(Items.LEATHER),
				new ItemStack(Items.LEATHER), new ItemStack(Items.LEATHER) },
				new ItemStack(LIGHT_POUCH)));
		GameRegistry.addRecipe(new ShapedRecipes(3, 3, new ItemStack[] {
				new ItemStack(BLOCK_LIGHT_AIR), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(BLOCK_LIGHT_AIR), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(Blocks.TNT), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(BLOCK_LIGHT_AIR), new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(BLOCK_LIGHT_AIR) }, new ItemStack(DARK_BOMB)));
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
										new ItemStack(COAL_DUST), in, false)
								|| OreDictionary.itemMatches(
										new ItemStack(COAL_BIT), in, false)
								|| OreDictionary.itemMatches(
										new ItemStack(GLOWSTONE_BIT), in, false)
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
								new ItemStack(COAL_DUST), in, false))
							lighter -= 4;
						else if (OreDictionary.itemMatches(
								new ItemStack(COAL_BIT), in, false))
							lighter--;
						else if (OreDictionary.itemMatches(
								new ItemStack(GLOWSTONE_BIT), in, false))
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
				return new ItemStack(Item.getItemFromBlock(BLOCK_LIGHT_AIR));
			}

			@Override
			public ItemStack[] getRemainingItems(InventoryCrafting inv)
			{
				return new ItemStack[0];
			}
		});
	}
}
