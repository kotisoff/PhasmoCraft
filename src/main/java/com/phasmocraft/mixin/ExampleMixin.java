package com.phasmocraft.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(MinecraftServer.class)
public abstract class ExampleMixin {
	@Shadow public abstract Iterable<ServerWorld> getWorlds();

	@Unique
	public abstract Consumer<? super ServerPlayerEntity> sendMessage(Text message);

	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		System.out.print("Да иди ты нахуй...\n");
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}