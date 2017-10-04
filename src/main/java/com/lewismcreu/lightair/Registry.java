package com.lewismcreu.lightair;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber
public class Registry {
	public static final CreativeTabs LIGHTAIR = new CreativeTabs("lightair") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(BLOCK_LIGHT_AIR));
		}
	};

	public static final BlockLightAir BLOCK_LIGHT_AIR = new BlockLightAir();
	public static final BlockStructuralAir BLOCK_STRUCTURAL_AIR = new BlockStructuralAir();
	public static final Item COAL_DUST = new Item()
			.setUnlocalizedName("coal_dust").setRegistryName("coal_dust")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item COAL_BIT = new Item()
			.setUnlocalizedName("coal_bit").setRegistryName("coal_bit")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item GLOWSTONE_BIT = new Item()
			.setUnlocalizedName("glowstone_bit")
			.setRegistryName("glowstone_bit")
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final Item LIGHT_POUCH = new ItemLightPouch();
	public static final Item DARK_BOMB = new ItemDarkBomb();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registry.registerAll(BLOCK_LIGHT_AIR, BLOCK_STRUCTURAL_AIR);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.registerAll(COAL_BIT, COAL_DUST, GLOWSTONE_BIT, LIGHT_POUCH,
				DARK_BOMB);
		registry.register(new ItemBlockLightAir(BLOCK_LIGHT_AIR));
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		IForgeRegistry<IRecipe> registry = event.getRegistry();
		ResourceLocation loc = new ResourceLocation(LightAir.MOD_ID, "recipes");

		// TODO convert to use oredictionary
		registry.register(new ShapelessOreRecipe(loc,
				new ItemStack(Registry.COAL_DUST, 4), new ItemStack(Items.COAL))
						.setRegistryName(LightAir.MOD_ID, "r_coal_to_dust"));
		registry.register(
				new ShapelessOreRecipe(loc, new ItemStack(Registry.COAL_BIT, 4),
						new ItemStack(Registry.COAL_DUST)).setRegistryName(
								LightAir.MOD_ID, "r_coal_dust_to_bit"));
		registry.register(new ShapelessOreRecipe(loc,
				new ItemStack(Registry.GLOWSTONE_BIT, 4),
				new ItemStack(Items.GLOWSTONE_DUST)).setRegistryName(
						LightAir.MOD_ID, "r_glow_dust_to_bit"));
		registry.register(new ShapedOreRecipe(loc, new ItemStack(Items.COAL),
				"xx", "xx", 'x', new ItemStack(Registry.COAL_DUST))
						.setRegistryName(LightAir.MOD_ID, "r_dust_to_coal"));
		registry.register(
				new ShapedOreRecipe(loc, new ItemStack(Registry.COAL_DUST),
						"xx", "xx", 'x', new ItemStack(Registry.COAL_BIT))
								.setRegistryName(LightAir.MOD_ID,
										"r_coal_bit_to_dust"));
		registry.register(
				new ShapedOreRecipe(loc, new ItemStack(Items.GLOWSTONE_DUST),
						"xx", "xx", 'x', new ItemStack(Registry.GLOWSTONE_BIT))
								.setRegistryName(LightAir.MOD_ID,
										"r_glow_bit_to_dust"));
		registry.register(new ShapelessOreRecipe(loc,
				new ItemStack(Registry.BLOCK_LIGHT_AIR),
				new ItemStack(Registry.COAL_DUST),
				new ItemStack(Items.GLOWSTONE_DUST))
						.setRegistryName(LightAir.MOD_ID, "r_lightair"));
		registry.register(new ShapedOreRecipe(loc,
				new ItemStack(Registry.LIGHT_POUCH), "x x", "yxy", "yyy", 'x',
				new ItemStack(Registry.BLOCK_LIGHT_AIR, 1, 15), 'y',
				new ItemStack(Items.LEATHER)).setRegistryName(LightAir.MOD_ID,
						"r_light_pouch"));
		registry.register(new ShapedOreRecipe(loc,
				new ItemStack(Registry.DARK_BOMB), "xyx", "yzy", "xyx", 'x',
				new ItemStack(Registry.BLOCK_LIGHT_AIR), 'y',
				new ItemStack(Blocks.OBSIDIAN), 'z', new ItemStack(Blocks.TNT))
						.setRegistryName(LightAir.MOD_ID, "r_dark_bomb"));
		registry.register(new RecipeLightAir().setRegistryName(LightAir.MOD_ID,
				"r_lightair_modifier"));
	}

	private static class RecipeLightAir
			extends
				IForgeRegistryEntry.Impl<IRecipe>
			implements
				IRecipe {
		@Override
		public boolean matches(InventoryCrafting inv, World worldIn) {
			List<ItemStack> input = new ArrayList<>();

			for (int i = 0; i < inv.getSizeInventory(); i++)
				if (inv.getStackInSlot(i) != null)
					input.add(inv.getStackInSlot(i));

			if (input.size() < 2)
				return false;

			int counter = 0;
			for (ItemStack i : input) {
				if (!isValidInput(i))
					return false;
				if (i.getItem() == Item.getItemFromBlock(BLOCK_LIGHT_AIR)) {
					counter++;
					if (counter > 1)
						return false;
				}
			}
			if (counter != 1)
				return false;

			return true;
		}

		private boolean isValidInput(ItemStack in) {
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
		public ItemStack getCraftingResult(InventoryCrafting inv) {
			if (!matches(inv, null))
				return null;
			ItemStack out = new ItemStack(
					Item.getItemFromBlock(BLOCK_LIGHT_AIR));
			int meta = 0;
			int lighter = 0;
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack in = inv.getStackInSlot(i);
				if (in != null) {
					if (in.getItem() == Item.getItemFromBlock(BLOCK_LIGHT_AIR))
						meta = in.getItemDamage();
					else if (OreDictionary.itemMatches(
							new ItemStack(Items.GLOWSTONE_DUST), in, false))
						lighter += 4;
					else if (OreDictionary.itemMatches(new ItemStack(COAL_DUST),
							in, false))
						lighter -= 4;
					else if (OreDictionary.itemMatches(new ItemStack(COAL_BIT),
							in, false))
						lighter--;
					else if (OreDictionary.itemMatches(
							new ItemStack(GLOWSTONE_BIT), in, false))
						lighter++;
				}
			}
			meta += lighter;
			meta = MathHelper.clamp(meta, 0, 15);
			out.setItemDamage(meta);
			return out;
		}

		@Override
		public ItemStack getRecipeOutput() {
			return new ItemStack(Item.getItemFromBlock(BLOCK_LIGHT_AIR));
		}

		@Override
		public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
			return NonNullList.create();
		}

		@Override
		public boolean canFit(int width, int height) {
			return (width * height) > 1;
		}
	}
}
