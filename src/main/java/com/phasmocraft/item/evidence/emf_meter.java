package com.phasmocraft.item.evidence;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class emf_meter extends Item {
    public emf_meter(Settings settings) {
        super(settings);
    }
    public float EMF = 0.0f;

    public void setEMF(float emf){
        this.EMF = emf;
    }

    public int doubleuse = 0;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(doubleuse==0) {
            playerEntity.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1.0F, 1.0F);
            if(EMF==0.0f){
                setEMF(0.1f);
                playerEntity.sendMessage(Text.literal("EMF On"));
            }else{
                setEMF(0.0f);
                playerEntity.sendMessage(Text.literal("EMF Off"));
            }
            doubleuse = 1;
        }else{
            doubleuse = 0;
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
