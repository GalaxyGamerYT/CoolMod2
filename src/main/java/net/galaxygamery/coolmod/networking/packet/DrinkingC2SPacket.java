package net.galaxygamery.coolmod.networking.packet;

import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.galaxygamery.coolmod.util.IEntityDataSaver;
import net.galaxygamery.coolmod.util.ThirstData;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class DrinkingC2SPacket {
    private static final String MESSAGE_DRINKING_WATER = "message.coolmod.drank_water";
    private static final String MESSAGE_NO_WATER_NEARBY = "message.coolmod.no_water";

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        //On the server
        ServerWorld world = (ServerWorld) player.getWorld();
        if (isAroundWaterThem(player, world,2)) {

            // Notify the player
            player.sendMessage(Text.translatable(MESSAGE_DRINKING_WATER)
                    .fillStyle(Style.EMPTY.withColor(Formatting.DARK_AQUA)), false);

            // Play the drinking sound
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,
                    0.5F, world.random.nextFloat() * 0.1F + 0.9F);

            // actually add the water level to the player
            ThirstData.addThirst(((IEntityDataSaver) player),1);

            // Output how much thirst player has
            player.sendMessage(Text.literal("Thirst: " + ((IEntityDataSaver) player).getPersistentData().getInt("thirst"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
        } else {
            player.sendMessage(Text.translatable(MESSAGE_NO_WATER_NEARBY)
                    .fillStyle(Style.EMPTY.withColor(Formatting.RED)), false);

            // Output how much thirst player has
            player.sendMessage(Text.literal("Thirst: " + ((IEntityDataSaver) player).getPersistentData().getInt("thirst"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);

            // Sync thirst
            ThirstData.syncThirst(((IEntityDataSaver) player).getPersistentData().getInt("thirst"), player);
        }
    }

    private static boolean isAroundWaterThem(ServerPlayerEntity player, ServerWorld world, int size) {
        return BlockPos.stream(player.getBoundingBox().expand(size))
                .map(world::getBlockState).filter(blockState -> blockState.isOf(Blocks.WATER)).toArray().length > 0;
    }
}
