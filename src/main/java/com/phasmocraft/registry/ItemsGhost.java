package com.phasmocraft.registry;

import com.phasmocraft.item.ghostAbilities.ghostEventItem;
import com.phasmocraft.item.ghostAbilities.ghostInteractItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.phasmocraft.Phasmo.*;
import static com.phasmocraft.registry.ModRegistryUtil.addItemsToGroup;

public class ItemsGhost {
    public static final ghostEventItem GHOST_EVENT_ITEM = new ghostEventItem(new FabricItemSettings().maxCount(1));
    public static final ghostInteractItem GHOST_INTERACT_ITEM = new ghostInteractItem(new FabricItemSettings().maxCount(1));

    public static void RegisterGhostItems(){
        Registry.register(Registries.ITEM, new Identifier(MODID, "ghost_event"), GHOST_EVENT_ITEM);
        Registry.register(Registries.ITEM, new Identifier(MODID, "ghost_interact"), GHOST_INTERACT_ITEM);
        addItemsToGroup(GROUP_GHOST, GHOST_EVENT_ITEM, GHOST_INTERACT_ITEM);
    }
}
