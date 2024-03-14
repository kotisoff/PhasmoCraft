package com.phasmocraft.block.evidence.util;

import com.phasmocraft.block.evidence.SaltBlockEntity;
import com.phasmocraft.block.test.uvPrintsBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class uvChargeable extends BlockEntity {

    public static IntProperty UV_LEVEL = IntProperty.of("uv_level", 0, 3);
    public static IntProperty UV_CHARGE = IntProperty.of("uv_charge", 0, 20);

    public uvChargeable(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        //TODO Auto-generated constructor stub
    }

    public static void onTick(World world, BlockPos pos, BlockState state, int tickCount) {
        if (world.isClient()) return;
        if (tickCount%3 == 0) {
            int uv_charge = state.get(UV_CHARGE);
            if (uv_charge > 0) {
                uv_charge--;
                world.setBlockState(pos, state.with(UV_LEVEL, Math.min(uv_charge,3)).with(UV_CHARGE, uv_charge));
            }
        }
    }

}
