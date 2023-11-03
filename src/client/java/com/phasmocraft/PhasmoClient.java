package com.phasmocraft;

import com.phasmocraft.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class PhasmoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelPredicateProviderRegistry.register(ModItems.EMF_METER, new Identifier(Phasmo.MODID, "emf"),
				(stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				assert stack.getNbt() != null;
				return stack.getNbt().getFloat("phasmocraft.emf");
			}
			return 0f;
		});
	}
}