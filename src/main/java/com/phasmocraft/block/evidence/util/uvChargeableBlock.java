package com.phasmocraft.block.evidence.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class uvChargeableBlock extends BlockWithEntity {
    public static IntProperty UV_LEVEL = IntProperty.of("uv_level", 0, 3);
    public static IntProperty UV_CHARGE = IntProperty.of("uv_charge", 0, 20);
    public static BooleanProperty STEPPEDON = BooleanProperty.of("steppedon");

    public static int uv_charge = 0;
    public static int uv_level = 0;
    private int tickCount = 0;

    public uvChargeableBlock(Settings settings){
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UV_LEVEL);
        builder.add(UV_CHARGE);
        super.appendProperties(builder);
    }

    public void discharge(BlockState state, World world, BlockPos pos) {
        if (world.isClient()) return;
        tickCount++;
        if (tickCount > 5) {
            tickCount = 0;
            uv_charge = state.get(UV_CHARGE);
            if (uv_charge > 0) {
                uv_charge--;
                world.setBlockState(pos, state.with(UV_LEVEL, Math.min(uv_charge,3)));
                uv_level = Math.min(uv_charge,3);
            }
        }
    }

    public static boolean canBeShown(World world, BlockPos pos, BlockState state){
        return true;
    }

    public static void charge(World world, BlockPos pos, BlockState state){
        if (world.isClient()) return;
        if (!state.get(STEPPEDON) || !canBeShown(world, pos, state)) return;
        uv_charge = state.get(UV_CHARGE);
        if (uv_charge < 20) {
            uv_charge++;
            uv_level = Math.min(uv_charge,3);
            world.setBlockState(pos, state.with(UV_CHARGE, uv_charge).with(UV_LEVEL, Math.min(uv_charge,3)));
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos arg0, BlockState arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBlockEntity'");
    }

    

}