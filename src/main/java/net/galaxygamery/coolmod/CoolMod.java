package net.galaxygamery.coolmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.galaxygamery.coolmod.block.ModBlocks;
import net.galaxygamery.coolmod.event.PlayerCopyHandler;
import net.galaxygamery.coolmod.event.PlayerTickHandler;
import net.galaxygamery.coolmod.item.ModItemGroups;
import net.galaxygamery.coolmod.item.ModItems;
import net.galaxygamery.coolmod.networking.ModMessages;
import net.galaxygamery.coolmod.util.ModLootTableModifiers;

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

		ModLootTableModifiers.modifyLootTables();

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);

		ModMessages.registerC2SPackets();

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
		ServerPlayerEvents.COPY_FROM.register(new PlayerCopyHandler());

		LOGGER.info("Cool Mod loaded!");
	}
}