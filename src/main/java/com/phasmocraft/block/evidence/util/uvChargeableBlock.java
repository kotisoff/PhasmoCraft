package com.phasmocraft.block.evidence.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class uvChargeableBlock extends Block {
    public static BooleanProperty UVSHOWN = BooleanProperty.of("uv_shown");

    public uvChargeableBlock(FabricBlockSettings settings){
        super(settings);
        setDefaultState(getDefaultState().with(UVSHOWN, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UVSHOWN);
        super.appendProperties(builder);
    }

    public boolean canBeShown(World world, BlockPos pos, BlockState state){
        return true;
    }

    public static void setShown(World world, BlockPos pos, BlockState state, boolean bool){
        world.setBlockState(pos, state.with(UVSHOWN, bool));
    }
}
