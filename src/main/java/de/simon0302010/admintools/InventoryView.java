package de.simon0302010.admintools;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class InventoryView {
    static int showInventory(CommandContext<ServerCommandSource> context) {
        try {
            ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player"); // target
            ServerPlayerEntity viewer = context.getSource().getPlayer(); // admin

            int chestSize = 54;
            SimpleInventory chestInventory = new SimpleInventory(chestSize);
            for (int i = 0; i < chestSize && i < player.getInventory().size(); i++) {
                chestInventory.setStack(i, player.getInventory().getStack(i).copy());
            }

            viewer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inv, p) ->
                    new ReadOnlyContainerScreenHandler(syncId, inv, chestInventory, 6),
                    Text.literal(player.getName().getString() + "'s Inventory")
            ));
        } catch (CommandSyntaxException e) {
            context.getSource().sendError(Text.literal("Player not found."));
        }
        return 1;
    }
}