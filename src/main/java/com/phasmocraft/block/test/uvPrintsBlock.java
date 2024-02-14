package com.phasmocraft.block.test;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class uvPrintsBlock extends Block {
    public static BooleanProperty SHOWN = BooleanProperty.of("shown");

    public uvPrintsBlock(FabricBlockSettings settings){
        super(settings);
        setDefaultState(getDefaultState().with(SHOWN, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHOWN);
        super.appendProperties(builder);
    }

    public static void setShown(World world, BlockPos pos, BlockState state, boolean bool){
        world.setBlockState(pos, state.with(SHOWN, bool));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
