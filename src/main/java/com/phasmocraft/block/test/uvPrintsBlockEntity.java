package com.phasmocraft.block.test;

import com.phasmocraft.block.evidence.SaltBlockEntity;
import com.phasmocraft.block.evidence.util.uvChargeable;
import com.phasmocraft.registry.BlocksEvidence;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class uvPrintsBlockEntity extends uvChargeable {

    int tickCount = 0;

    public uvPrintsBlockEntity(BlockPos pos, BlockState state) {
        super(BlocksEvidence.UV_PRINTS_BLOCK_ENTITY, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state, uvPrintsBlockEntity blockEntity) {
        tickCount++;
        uvChargeable.onTick(world, pos, state, tickCount);
    }
}
