package com.phasmocraft.block.evidence;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
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

public class salt extends Block {
    public static final BooleanProperty STEPPEDON = BooleanProperty.of("steppedon");
    public salt(Block.Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(STEPPEDON, false));
    }
//    @Override
//    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
//        if(world.isClient()) return;
//        if(!state.get(STEPPEDON)){
//            entity.sendMessage(Text.literal("Ты сука, наступил на мою соль блять."));
//            world.setBlockState(pos, state.with(STEPPEDON, true));
//        }
//    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(world.isClient()) return;
        if(!state.get(STEPPEDON) & entity.isAlive()){
            entity.sendMessage(Text.literal("Ты сука, наступил на мою соль блять."));
            world.setBlockState(pos, state.with(STEPPEDON, true));
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2f, 0f, 0.2f, 0.8f, 0.1f, 0.8f);
//        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f,0f,0f,1f,0.05f,1f);
//        return super.getCollisionShape(state, world, pos, context);
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STEPPEDON);
        super.appendProperties(builder);
    }
}
