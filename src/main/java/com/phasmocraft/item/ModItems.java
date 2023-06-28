package com.phasmocraft.item;

import com.phasmocraft.item.evidence.emf_meter;
import com.phasmocraft.item.evidence.uv_flashlight;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.phasmocraft.Phasmo.MODID;

public class ModItems {
    public static final emf_meter EMF_METER =
            Registry.register(Registries.ITEM,new Identifier(MODID,"emf_meter"),
                    new emf_meter(new FabricItemSettings().maxCount(1)));
    public static final uv_flashlight UV_FLASHLIGHT =
            Registry.register(Registries.ITEM,new Identifier(MODID,"uv_flashlight"),
                    new uv_flashlight(new FabricItemSettings().maxCount(1)));
}
