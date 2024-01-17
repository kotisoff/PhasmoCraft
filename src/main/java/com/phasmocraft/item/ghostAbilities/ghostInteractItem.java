package com.phasmocraft.item.ghostAbilities;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;

public class ghostInteractItem extends Item {
    public ghostInteractItem(FabricItemSettings settings){
        super(settings);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        player.sendMessage(Text.literal("Action: "+"test"));
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }
}
