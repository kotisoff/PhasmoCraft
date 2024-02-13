package com.phasmocraft.item.evidence;

import com.phasmocraft.events.ghostEvent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class emf_meter extends Item {
    private static final String nbtEnabled = "phasmocraft.enabled";
    private static final String nbtEmf = "phasmocraft.emf";
    public emf_meter(Settings settings) {
        super(settings);
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean(nbtEnabled, false);
        nbt.putFloat(nbtEmf, 0);
        getDefaultStack().setNbt(nbt);
    }

    public void setEMF(PlayerEntity player, Hand hand, float emf) {
        setEMF(player.getStackInHand(hand), emf);
    }
    public void setEMF(ItemStack stack, float emf){
        if(!isEnabled(stack)) return;
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        if(emf<0.2f) emf=0.2f;
        nbt.putFloat(nbtEmf, emf);
        stack.setNbt(nbt);
    }

    public boolean isEnabled(ItemStack stack){
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        return nbt.getBoolean(nbtEnabled);
    }
    public void setEnabled(ItemStack stack, boolean state){
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        nbt.putFloat(nbtEmf, (state) ? 0.2f : 0f);
        nbt.putBoolean(nbtEnabled, state);
    }
    public void toggleEnabled(ItemStack stack){
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        boolean enabled = nbt.getBoolean(nbtEnabled);
        nbt.putFloat(nbtEmf, (!enabled) ? 0.2f : 0f);
        nbt.putBoolean(nbtEnabled, !enabled);
    }

    public float getEMF(ItemStack stack){
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            return stack.getNbt().getFloat("phasmocraft.emf");
        }
        return 0f;
    }
    public float getEMF(PlayerEntity player, Hand hand){
        ItemStack stack = player.getStackInHand(hand);
        return getEMF(stack);
    }

    public void playEmfSound(ServerWorld world, Vec3d pos, float emf){
        world.playSound(pos.x, pos.y, pos.z, SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value(), SoundCategory.VOICE, 1, 1+emf, false);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1.0F, 1.0F);
        if (!world.isClient()) {
            toggleEnabled(player.getStackInHand(hand));
        }
        return super.use(world, player, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
//        return getEMF(stack) != 0f;
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            int EMF = (int)(stack.getNbt().getFloat("phasmocraft.emf")*5);
            if(EMF == 0f){
                tooltip.add(Text.of("EMF: Off"));
            }
            else{
                tooltip.add(Text.of("EMF: "+EMF));
            }
        }
    }
}
