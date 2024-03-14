package com.phasmocraft.block.evidence;

import com.phasmocraft.registry.BlocksEvidence;
import com.phasmocraft.block.evidence.util.uvChargeable;
import com.phasmocraft.block.evidence.util.uvChargeableBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SaltBlockEntity extends uvChargeable {

    int tickCount = 0;

    public SaltBlockEntity(BlockPos pos, BlockState state) {
        super(BlocksEvidence.SALT_BLOCK_ENTITY, pos, state);
    }

    
    public void tick(World world, BlockPos pos, BlockState state, SaltBlockEntity blockEntity) {
        tickCount++;
        uvChargeable.onTick(world, pos, state, blockEntity, tickCount);
    }


}
