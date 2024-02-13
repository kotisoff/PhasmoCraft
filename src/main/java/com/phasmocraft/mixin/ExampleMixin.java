package com.phasmocraft.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.server.PlayerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(PlayerManager.class)
public abstract class ExampleMixin {
	@Inject(method = "onPlayerConnect", at = @At(value = "HEAD"))
	private void init(CallbackInfo info) {
		System.out.print("Да иди ты нахуй...\n");
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}