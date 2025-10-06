package de.simon0302010.admintools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.entity.player.PlayerInventory;

public class ReadOnlyContainerScreenHandler extends GenericContainerScreenHandler {
    public ReadOnlyContainerScreenHandler(int syncId, PlayerInventory playerInv, Inventory inv, int rows) {
        super(ScreenHandlerType.GENERIC_9X6, syncId, playerInv, inv, rows);
    }

    @Override
    public boolean canInsertIntoSlot(Slot slot) {
        return false;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onSlotClick(int slot, int button, SlotActionType actionType, PlayerEntity player) {
    }
}
