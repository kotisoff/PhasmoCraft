package com.phasmocraft.block.evidence;

import com.phasmocraft.block.evidence.util.uvChargeableBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class salt extends uvChargeableBlock {
    public static final BooleanProperty STEPPEDON = BooleanProperty.of("steppedon");
    public static final DirectionProperty FACING = Properties.FACING;

    public salt(FabricBlockSettings settings){
        super(settings);
        setDefaultState(getDefaultState().with(STEPPEDON, false));
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!state.get(STEPPEDON) & entity.isAlive()){
            entity.playSound(SoundEvents.BLOCK_SAND_STEP, 1, 1);
            if(world.isClient()) return;
            world.setBlockState(pos, state.with(STEPPEDON, true));
        }
    }

    @Override
    public boolean canBeShown(World world, BlockPos pos, BlockState state) {
        return state.get(STEPPEDON);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2f, 0f, 0.2f, 0.8f, 0.1f, 0.8f);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f,0f,0f,1f,0f,1f);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STEPPEDON);
        builder.add(FACING);
        super.appendProperties(builder);
    }
}
