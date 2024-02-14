package com.phasmocraft.item.evidence;

import com.phasmocraft.block.test.uvPrintsBlock;
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
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import static com.phasmocraft.registry.BlocksEvidence.UV_PRINTS_BLOCK;

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

    private void raycastUV(World world, PlayerEntity player){
        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.NONE);
        BlockPos pos = hit.getBlockPos();
        player.sendMessage(Text.literal("Looking at pos: "+pos.toString()));
        BlockState state = world.getBlockState(pos);
        player.sendMessage(Text.literal("Found UV Block: "+state.isOf(UV_PRINTS_BLOCK)));
        if(state.isOf(UV_PRINTS_BLOCK)) ((uvPrintsBlock) state.getBlock()).setShown(world, pos, true);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        setDefaultNbt(stack, world);
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
        raycastUV(world, user);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if(nbt == null) return false;
        return nbt.getBoolean(nbtEnabled);
    }
}
