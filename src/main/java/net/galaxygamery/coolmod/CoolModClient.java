package net.galaxygamery.coolmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.galaxygamery.coolmod.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class CoolModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // Rendering cut out in blocks
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_TRAPDOOR, RenderLayer.getCutout());
    }
}
