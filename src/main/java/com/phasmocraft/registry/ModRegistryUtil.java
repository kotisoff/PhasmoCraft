package com.phasmocraft.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

import java.util.Optional;

public class ModRegistryUtil {
    private static RegistryKey<ItemGroup> GetGroupRegKey(ItemGroup Group){
        Optional<RegistryKey<ItemGroup>> Key = Registries.ITEM_GROUP.getKey(Group);
        return Key.orElse(ItemGroups.BUILDING_BLOCKS);
    }
    static void addItemsToGroup(ItemGroup group, Item... items){
        ItemGroupEvents.modifyEntriesEvent(GetGroupRegKey(group)).register(entries -> {
            for (Item item : items) {
                entries.add(item);
            }
        });
    }
}
