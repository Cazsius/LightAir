package com.lewismcreu.lightair;

import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy {

	public void preInit() {
	}

	public void init() {
		OreDictionary.registerOre("dustCoal", Registry.COAL_DUST);
		OreDictionary.registerOre("coal_dust", Registry.COAL_DUST);

		OreDictionary.registerOre("bitGlowstone", Registry.GLOWSTONE_BIT);
		OreDictionary.registerOre("glowstone_bit", Registry.GLOWSTONE_BIT);

		OreDictionary.registerOre("bitCoal", Registry.COAL_BIT);
		OreDictionary.registerOre("coal_bit", Registry.COAL_BIT);
	}
}
