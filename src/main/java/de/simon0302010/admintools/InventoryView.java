package de.simon0302010.admintools;

import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.text.Text;

public class InventoryView {
    static int showInventory(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(() -> Text.literal("Not implemented yet."), false);
        return 1;
    }
}
