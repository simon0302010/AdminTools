package de.simon0302010.admintools;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.text.Text;

public class PlayerLocator
{
    static int getLocation(CommandContext<ServerCommandSource> context) {
        try {
            ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
            // get clean string of world player is in
            String playerWorld = player.getWorld().getRegistryKey().getValue().toString().replaceFirst("^minecraft:", "").replaceFirst("^the_", "");
            context.getSource().sendFeedback(
                    () -> Text.literal(
                            "%s is at X=%.2f, Y=%.2f, Z=%.2f in %s".formatted(player.getName().getString(), player.getX(), player.getY(), player.getZ(), playerWorld.substring(0,1).toUpperCase() + playerWorld.substring(1).toLowerCase())
                    ),
                    false
            );
        } catch (CommandSyntaxException e) {
            context.getSource().sendError(Text.literal("Player not found."));
        }
        return 1;
    }
}
