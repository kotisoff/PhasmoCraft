package com.phasmocraft.items.evidence;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class emf_meter extends Item {
    public emf_meter(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        playerEntity.playSound(SoundEvents.BLOCK_ANVIL_PLACE,1.0F,1.0F);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
