package com.phasmocraft.block.evidence;

import com.phasmocraft.registry.BlocksEvidence;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SaltBlockEntity extends BlockEntity {
    public SaltBlockEntity(BlockPos pos, BlockState state) {
        super(BlocksEvidence.SALT_BLOCK_ENTITY, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state, SaltBlockEntity blockEntity) {
    }
}
