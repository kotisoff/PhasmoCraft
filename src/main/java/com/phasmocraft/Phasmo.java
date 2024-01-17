package com.phasmocraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.phasmocraft.registry.BlocksEvidence.*;
import static com.phasmocraft.registry.ItemsEvidence.*;
import static com.phasmocraft.registry.ItemsGhost.*;

public class Phasmo implements ModInitializer {
	public static final String MODID = "phasmocraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final ItemGroup GROUP_EVIDENCE =
			FabricItemGroup.builder()
					.icon(()->new ItemStack(EMF_METER))
					.displayName(Text.translatable("itemGroup.phasmocraft.evidence"))
					.build();

	public static final ItemGroup GROUP_GHOST =
			FabricItemGroup.builder()
					.icon(() -> new ItemStack(EMF_METER))
					.displayName(Text.translatable("itemGroup.phasmocraft.ghost"))
					.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.ITEM_GROUP, new Identifier(MODID,"group_evidence"), GROUP_EVIDENCE);
		Registry.register(Registries.ITEM_GROUP, new Identifier(MODID,"group_ghost"), GROUP_GHOST);
		RegisterEvidenceItems();
		RegisterGhostItems();
		RegisterBlocks();
	}
}