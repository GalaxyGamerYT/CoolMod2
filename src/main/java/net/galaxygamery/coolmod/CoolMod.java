package net.galaxygamery.coolmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.galaxygamery.coolmod.block.ModBlocks;
import net.galaxygamery.coolmod.item.ModItemGroups;
import net.galaxygamery.coolmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoolMod implements ModInitializer {
	public static final String MOD_ID = "coolmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);

		LOGGER.info("Cool Mod loaded!");
	}
}