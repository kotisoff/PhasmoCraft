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

public class uv_flashlight extends flashlight {
    private static final String nbtEnabled = "phasmocraft.enabled";

    public uv_flashlight(Settings settings){
        super(settings);
    }

    private void raycastUV(World world, PlayerEntity player, ItemStack stack){
        if(!isEnabled(stack)) return;
        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.NONE);
        BlockPos pos = hit.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if(state.contains(uvChargeableBlock.UVSHOWN)){
            uvChargeableBlock block = (uvChargeableBlock) state.getBlock();
            if(block.canBeShown(world, pos, state)) uvChargeableBlock.setShown(world, pos, state, true);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(selected) raycastUV(world, (PlayerEntity) entity, stack);
    }
}
