package com.phasmocraft.item.evidence;

import com.phasmocraft.Phasmo;
import com.phasmocraft.block.evidence.util.uvChargeableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class uv_flashlight extends flashlight {
    public uv_flashlight(Settings settings){
        super(settings);
    }
    
    public static IntProperty UV_LEVEL = IntProperty.of("uv_level", 0, 3);

    private void raycastUV(World world, PlayerEntity player, ItemStack stack){
        if(!isEnabled(stack)) return;
        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.NONE);
        BlockPos pos = hit.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if(state.contains(UV_LEVEL)){
            //Phasmo.LOGGER.info("ПОЙМАН!");
            uvChargeableBlock block = (uvChargeableBlock) state.getBlock();
            if(block.canBeShown(world, pos, state)) uvChargeableBlock.charge(world, pos, state);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(selected) raycastUV(world, (PlayerEntity) entity, stack);
    }
}
