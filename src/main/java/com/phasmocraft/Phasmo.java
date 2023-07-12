package com.phasmocraft;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.phasmocraft.item.ModItems.*;

public class Phasmo implements ModInitializer {
	public static final String MODID = "phasmocraft";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	private static final ItemGroup PHASMO_GROUP =
			FabricItemGroup.builder(new Identifier(MODID,"group"))
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