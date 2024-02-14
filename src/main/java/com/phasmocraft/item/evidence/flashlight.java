package com.phasmocraft.item.evidence;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class flashlight extends Item {
    private static final String nbtEnabled = "phasmocraft.enabled";

    public flashlight(Item.Settings settings){
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world.isClient()) return;
        NbtCompound nbt = stack.getNbt();
        if(nbt != null) return;
        nbt = new NbtCompound();
        nbt.putBoolean(nbtEnabled, false);
        stack.setNbt(nbt);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1, 1);
        if(world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        nbt.putBoolean(nbtEnabled, !nbt.getBoolean(nbtEnabled));
        user.sendMessage(Text.literal(nbt.toString()));
        stack.setNbt(nbt);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if(nbt == null) return false;
        return nbt.getBoolean(nbtEnabled);
    }
}
