package com.phasmocraft.block.evidence.util;

import static com.phasmocraft.Phasmo.LOGGER;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class uvChargeableBlock extends Block {
    public static IntProperty UV_LEVEL = IntProperty.of("uv_level", 0, 3);

    public int uv_charge = 0;
    public int uv_level = 0;
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

    public void discharge(BlockState state, World world, BlockPos pos) {
        if (world.isClient()) return;
        if (uv_charge > 0) {
            uv_charge--;
            world.setBlockState(pos, state.with(UV_LEVEL, Math.min(uv_charge,3)));
            uv_level = Math.min(uv_charge,3);
        }
    }

    public boolean canBeShown(World world, BlockPos pos, BlockState state){
        return true;
    }

    public static void charge(World world, BlockPos pos, BlockState state){
        return;
    }

    

}