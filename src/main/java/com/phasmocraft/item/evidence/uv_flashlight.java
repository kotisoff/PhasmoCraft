package com.phasmocraft.item.evidence;

import com.phasmocraft.block.evidence.util.uvChargeableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class uv_flashlight extends Item {
    private static final String nbtEnabled = "phasmocraft.enabled";

    public uv_flashlight(Settings settings){
        super(settings);
    }

    private void setDefaultNbt(ItemStack stack, World world){
        if(world.isClient()) return;
        NbtCompound nbt = stack.getNbt();
        if(nbt != null) return;
        nbt = new NbtCompound();
        nbt.putBoolean(nbtEnabled, false);
        stack.setNbt(nbt);
    }

    private void raycastUV(World world, PlayerEntity player, ItemStack stack){
        if(!isEnabled(stack)) return;
        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.NONE);
        BlockPos pos = hit.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if(state.contains(uvChargeableBlock.UVSHOWN)){
            uvChargeableBlock Block = (uvChargeableBlock) state.getBlock();
            if(Block.canBeShown(world, pos, state)) uvChargeableBlock.setShown(world, pos, state, true);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        setDefaultNbt(stack, world);
        if(selected) raycastUV(world, (PlayerEntity) entity, stack);
    }

    public boolean isEnabled(ItemStack stack){
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        return nbt.getBoolean(nbtEnabled);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1, 1);
        if(world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        nbt.putBoolean(nbtEnabled, !nbt.getBoolean(nbtEnabled));
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
