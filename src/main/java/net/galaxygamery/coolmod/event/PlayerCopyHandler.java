package net.galaxygamery.coolmod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.galaxygamery.coolmod.util.IEntityDataSaver;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCopyHandler implements ServerPlayerEvents.CopyFrom{
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        ((IEntityDataSaver) newPlayer).getPersistentData().putInt("Thirst",
                ((IEntityDataSaver) oldPlayer).getPersistentData().getInt("thirst"));
    }
}
