package com.phasmocraft.block.evidence.util;

import static com.phasmocraft.Phasmo.LOGGER;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class uvChargeableBlock extends Block {
    public static IntProperty UV_LEVEL = IntProperty.of("uv_level", 0, 3);

    public static int uv_charge = 0;
    public static int uv_level = 0;

    public uvChargeableBlock(FabricBlockSettings settings){
        super(settings);
        setDefaultState(getDefaultState().with(UV_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UV_LEVEL);
        super.appendProperties(builder);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.FAIL;
        if (uv_charge > 0) {
            uv_charge--;
            world.setBlockState(pos, state.with(UV_LEVEL, Math.min(uv_charge,3)));
            uv_level = Math.min(uv_charge,3);
        }
        LOGGER.info("DISCHARGED uv_charge "+uv_charge+", UV_LEVEL "+Math.min(uv_charge,3));
        return ActionResult.SUCCESS;
    }


    // Wizard v1.2 13b Q4_0 GGUF
    // but ITS NOT WORKING 😡👿
    /*@Override
    public void onTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(UV_CHARGE) > 0) {
            world.setBlockState(pos, state.with(UV_CHARGE, state.get(UV_CHARGE)-1));
        }
    }*/

    public boolean canBeShown(World world, BlockPos pos, BlockState state){
        return true;
    }

    public static void charge(World world, BlockPos pos, BlockState state){
        if (world.isClient()) return;
        if (uv_charge < 20) {
            uv_level = Math.min(uv_charge++,3);
            world.setBlockState(pos, state.with(UV_LEVEL, Math.min(uv_charge,3)));
        }
        LOGGER.info("CHARGED uv_charge "+uv_charge+", UV_LEVEL "+Math.min(uv_charge,3));
    }

}