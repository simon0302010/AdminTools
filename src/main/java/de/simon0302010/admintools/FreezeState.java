package de.simon0302010.admintools;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FreezeState {
    private static final Map<UUID, Vec3d> frozenPlayers = new HashMap<>();
    
    public static boolean isFrozen(ServerPlayerEntity player) {
        return frozenPlayers.containsKey(player.getUuid());
    }
    
    public static void freeze(ServerPlayerEntity player) {
        frozenPlayers.put(player.getUuid(), player.getPos());
    }
    
    public static void unfreeze(ServerPlayerEntity player) {
        frozenPlayers.remove(player.getUuid());
    }
    
    public static Vec3d getFrozenPosition(ServerPlayerEntity player) {
        return frozenPlayers.get(player.getUuid());
    }
    
    public static Map<UUID, Vec3d> getAllFrozen() {
        return frozenPlayers;
    }

    public static void clearAll() {
        frozenPlayers.clear();
    }
}