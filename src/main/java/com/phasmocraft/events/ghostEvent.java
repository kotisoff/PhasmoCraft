package com.phasmocraft.events;

import com.phasmocraft.Phasmo;
import com.phasmocraft.registry.ItemsEvidence;
import com.phasmocraft.util.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ghostEvent {
    private static final int EVENT_RADIUS = 5;

    public static void startGhostEvent(ServerWorld world, Vec3d pos, float emf, int ticks) {
        world.getServer().execute(() -> {
            for (PlayerEntity player : world.getPlayers()) {
                Vec3d playerPos = player.getPos();
                double distanceSq = playerPos.squaredDistanceTo(pos);

                Phasmo.LOGGER.info(distanceSq+" "+playerPos);

                if (distanceSq <= EVENT_RADIUS * EVENT_RADIUS) {
                    float newEmf = emf * (1.1F - (float) Math.sqrt(distanceSq) / EVENT_RADIUS);
                    newEmf = (float) Math.round(newEmf * 10) / 10;

                    for (ServerPlayerEntity serverPlayer : world.getPlayers()) {
                        serverPlayer.sendMessage(Text.literal("Dist: "+distanceSq+"\nPos: "+playerPos+"\nNew EMF: "+newEmf*5));
                    }

                    // Найдем все предметы EMF в инвентаре игрока
                    List<ItemStack> emfItems = Inventory.findAllItems(player, ItemsEvidence.EMF_METER);

                    // Обновим EMF для всех найденных предметов
                    for (ItemStack emfItem : emfItems) {
                        Phasmo.LOGGER.info("set emf: "+newEmf);
                        ItemsEvidence.EMF_METER.setEMF(emfItem, newEmf);
                        ItemsEvidence.EMF_METER.playEmfSound(world, pos, newEmf);
                    }
                }
            }
        });
    }
}
