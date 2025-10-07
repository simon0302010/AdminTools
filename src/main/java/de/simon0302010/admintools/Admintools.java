package de.simon0302010.admintools;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Admintools implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("locateplayer")
                    .requires(source -> source.hasPermissionLevel(1))
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                            .executes(PlayerLocator::getLocation)
                    )
            );
            dispatcher.register(CommandManager.literal("invsee")
                    .requires(source -> source.hasPermissionLevel(1))
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                            .executes(InventoryView::showInventory)
                    )
            );
            dispatcher.register(CommandManager.literal("freeze")
                    .requires(source -> source.hasPermissionLevel(1))
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                            .executes(FreezePlayer::freezePlayer)
                    )
            );
            dispatcher.register(CommandManager.literal("unfreeze")
                    .requires(source -> source.hasPermissionLevel(1))
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                            .executes(FreezePlayer::unfreezePlayer)
                    )
            );
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (FreezeState.isFrozen(player)) {
                    Vec3d frozenPos = FreezeState.getFrozenPosition(player);
                    player.teleport(frozenPos.x, frozenPos.y, frozenPos.z, false);
                    player.setVelocity(Vec3d.ZERO);
                    player.fallDistance = 0;
                }
            }
        });
    }
}