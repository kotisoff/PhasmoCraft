package com.phasmocraft.item.ghostAbilities;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;

public class ghostEventItem extends Item {
    public ghostEventItem(FabricItemSettings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) return super.useOnBlock(context);
        Vec3d pos = context.getBlockPos().toCenterPos();
        com.phasmocraft.events.ghostEvent.startGhostEvent((ServerWorld) context.getWorld(), pos, 1, 400);
        return super.useOnBlock(context);
    }
}
