package com.phasmocraft;

import com.phasmocraft.items.evidence.emf_meter;
import com.phasmocraft.items.evidence.uv_flashlight;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Phasmo implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("phasmocraft");
	public static final emf_meter EMF_METER =
			Registry.register(Registries.ITEM,new Identifier("phasmocraft","emf_meter"),
			new emf_meter(new FabricItemSettings().maxCount(1)));
	public static final uv_flashlight UV_FLASHLIGHT =
			Registry.register(Registries.ITEM,new Identifier("phasmocraft","uv_flashlight"),
			new uv_flashlight(new FabricItemSettings().maxCount(1)));
	private static final ItemGroup PHASMO_GROUP =
			FabricItemGroup.builder(new Identifier("phasmocraft","group"))
			.icon(()->new ItemStack(EMF_METER))
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
		ItemGroupEvents.modifyEntriesEvent(PHASMO_GROUP).register(content -> {
			content.add(EMF_METER);
			content.add(UV_FLASHLIGHT);
		});
	}
}