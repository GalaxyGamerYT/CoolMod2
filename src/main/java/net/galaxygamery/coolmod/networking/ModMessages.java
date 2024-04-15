package net.galaxygamery.coolmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.galaxygamery.coolmod.CoolMod;
import net.galaxygamery.coolmod.networking.packet.DrinkingC2SPacket;
import net.galaxygamery.coolmod.networking.packet.ExampleC2SPacket;
import net.galaxygamery.coolmod.networking.packet.ThirstSyncDataS2CPacket;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier DRINKING_ID = new Identifier(CoolMod.MOD_ID, "drinking");
    public static final Identifier THIRST_SYNC_ID = new Identifier(CoolMod.MOD_ID, "thirst_sync");
    public static final Identifier EXAMPLE_ID = new Identifier(CoolMod.MOD_ID, "example");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(DRINKING_ID, DrinkingC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(THIRST_SYNC_ID, ThirstSyncDataS2CPacket::receive);

    }
}
