package com.phasmocraft.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public static ItemStack findItem(PlayerEntity player, Item targetItem) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == targetItem) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static List<ItemStack> findAllItems(PlayerEntity player, Item targetItem) {
        List<ItemStack> matchingItems = new ArrayList<>();
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == targetItem) {
                matchingItems.add(stack);
            }
        }
        return matchingItems;
    }
}