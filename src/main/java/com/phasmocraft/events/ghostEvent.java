package com.phasmocraft.events;

import com.phasmocraft.util.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import com.phasmocraft.item.ModItems;

import java.util.List;

public class ghostEvent {
    private static final int TICKS_PER_UPDATE = 5;
    private static final int EVENT_RADIUS = 10;

    public static void startGhostEvent(ServerWorld world, Vec3d pos, float emf) {
        world.getServer().execute(() -> {
            for (PlayerEntity player : world.getPlayers()) {
                Vec3d playerPos = player.getPos();
                double distanceSq = playerPos.squaredDistanceTo(pos);

                if (distanceSq <= EVENT_RADIUS * EVENT_RADIUS) {
                    float newEmf = emf * (1.0F - (float) Math.sqrt(distanceSq) / EVENT_RADIUS);

                    // Найдем все предметы EMF в инвентаре игрока
                    List<ItemStack> emfItems = Inventory.findAllItems(player, ModItems.EMF_METER);

                    // Обновим EMF для всех найденных предметов
                    for (ItemStack emfItem : emfItems) {
                        updateEMF(emfItem, newEmf);
                    }
                }
            }
        });
    }

    private static void updateEMF(ItemStack emfItem, float emf) {
        if (!emfItem.isEmpty()) {
            if (!emfItem.hasNbt()) {
                emfItem.setNbt(new NbtCompound());
            }

            NbtCompound nbt = emfItem.getOrCreateNbt();
            nbt.putFloat("phasmocraft.emf",emf);
        }
    }
}