package com.phasmocraft.item.evidence;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class emf_meter extends Item {
    public emf_meter(Settings settings) {
        super(settings);
    }

    public void setEMF(PlayerEntity player, Hand hand, float emf) {
        NbtCompound nbt = new NbtCompound();
        nbt.putFloat("phasmocraft.emf",emf);
        player.getStackInHand(hand).setNbt(nbt);
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1.0F, 1.0F);
        if (!world.isClient()) {
            float EMF = getEMF(player,hand);
            if(EMF<1f){
                setEMF(player,hand,EMF+0.2f);
            }
            else{
                setEMF(player,hand,0f);
            }
        }
        return super.use(world, player, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getEMF(stack) != 0f;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            int EMF = (int)(stack.getNbt().getFloat("phasmocraft.emf")*10);
            if(EMF == 0f){
                tooltip.add(Text.of("EMF: Off"));
            }
            else{
                tooltip.add(Text.of("EMF: "+EMF));
            }
        }
    }
}
