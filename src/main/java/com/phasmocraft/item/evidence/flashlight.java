package com.phasmocraft.item.evidence;

import com.phasmocraft.block.evidence.util.uvChargeableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class flashlight extends Item {
    private static final String nbtEnabled = "phasmocraft.enabledSUKA";

    public flashlight(Item.Settings settings){
        super(settings);
    }

    public NbtCompound getDefaultNbt(){
        NbtCompound nbt = new NbtCompound();
        nbt.putFloat(nbtEnabled, 0.0f);
        return nbt;
    }

    private void setDefaultNbt(ItemStack stack){
        NbtCompound nbt = stack.getNbt();
        if(nbt != null) return;
        stack.setNbt(getDefaultNbt());
    }

    public float isEnabled(ItemStack stack){
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        return nbt.getFloat(nbtEnabled);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1, 1);
        if(world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        nbt.putFloat(nbtEnabled, 1.0f - nbt.getFloat(nbtEnabled));
        stack.setNbt(nbt);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if(nbt == null){
            setDefaultNbt(stack);
            return false;
        }
        return nbt.getFloat(nbtEnabled)>0?true:false;
    }
}
