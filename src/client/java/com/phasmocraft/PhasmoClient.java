package com.phasmocraft;

import com.phasmocraft.registry.ItemsEvidence;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class PhasmoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelPredicateProviderRegistry.register(ItemsEvidence.EMF_METER, new Identifier(Phasmo.MODID, "emf"),
				(stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				assert stack.getNbt() != null;
				return stack.getNbt().getFloat("phasmocraft.emf");
			}
			return 0f;
		});

		ModelPredicateProviderRegistry.register(ItemsEvidence.FLASHLIGHT, new Identifier(Phasmo.MODID, "enabled"),
				(stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				assert stack.getNbt() != null;
				return stack.getNbt().getFloat("phasmocraft.enabledSUKA");
			}
			return 0f;
		});
	}
}