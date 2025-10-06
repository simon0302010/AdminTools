package de.simon0302010.admintools;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;

public class FreezePlayer {
    static int freezePlayer(CommandContext<ServerCommandSource> context) {
        try {
            ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
            ServerPlayerEntity viewer = context.getSource().getPlayer();
            StatusEffectInstance effect = new StatusEffectInstance(
                    StatusEffects.RESISTANCE,
                    Integer.MAX_VALUE,
                    254,
                    true,
                    false,
                    false
            );
            player.addStatusEffect(effect);
            player.sendMessage(Text.literal("§aYou are now frozen."));
            context.getSource().sendFeedback(
                    () -> Text.literal(
                            "%s is now frozen.".formatted(player.getName().getString())
                    ),
                    false
            );
        } catch (CommandSyntaxException e) {
            context.getSource().sendError(Text.literal("Player not found."));
        }
        return 1;
    }
    static int unfreezePlayer(CommandContext<ServerCommandSource> context) {
        try {
            ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
            ServerPlayerEntity viewer = context.getSource().getPlayer();
            player.removeStatusEffect(StatusEffects.RESISTANCE);
            player.sendMessage(Text.literal("§aYou are no longer frozen."));
            context.getSource().sendFeedback(
                    () -> Text.literal(
                            "%s is no longer frozen.".formatted(player.getName().getString())
                    ),
                    false
            );
        } catch (CommandSyntaxException e) {
            context.getSource().sendError(Text.literal("Player not found."));
        }
        return 1;
    }
}
