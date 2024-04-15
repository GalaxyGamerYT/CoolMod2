package net.galaxygamery.coolmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.galaxygamery.coolmod.block.ModBlocks;
import net.galaxygamery.coolmod.client.ThirstHudOverlay;
import net.galaxygamery.coolmod.event.ClientPlayConnectionJoin;
import net.galaxygamery.coolmod.event.KeyInputHandler;
import net.galaxygamery.coolmod.networking.ModMessages;
import net.minecraft.client.render.RenderLayer;

public class CoolModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // Rendering cut out in blocks
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_TRAPDOOR, RenderLayer.getCutout());

        KeyInputHandler.register();
        ModMessages.registerS2CPackets();

        HudRenderCallback.EVENT.register(new ThirstHudOverlay());
        ClientPlayConnectionEvents.JOIN.register(new ClientPlayConnectionJoin());
    }

}
