
package com.phasmocraft.registry;

import com.phasmocraft.item.evidence.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.phasmocraft.Phasmo.*;
import static com.phasmocraft.registry.ModRegistryUtil.*;

public class ItemsEvidence {
    public static final emf_meter EMF_METER = new emf_meter(new FabricItemSettings().maxCount(1));
    public static final uv_flashlight UV_FLASHLIGHT = new uv_flashlight(new FabricItemSettings().maxCount(1));
    public static final flashlight FLASHLIGHT = new flashlight(new FabricItemSettings().maxCount(1));
    public static final strong_flashlight STRONG_FLASHLIGHT = new strong_flashlight(new FabricItemSettings().maxCount(1));

    public static void RegisterEvidenceItems(){
        Registry.register(Registries.ITEM,new Identifier(MODID,"emf_meter"), EMF_METER);
        Registry.register(Registries.ITEM,new Identifier(MODID,"uv_flashlight"), UV_FLASHLIGHT);
        Registry.register(Registries.ITEM, new Identifier(MODID, "flashlight"), FLASHLIGHT);
        Registry.register(Registries.ITEM, new Identifier(MODID, "strong_flashlight"), STRONG_FLASHLIGHT);
        addItemsToGroup(GROUP_EVIDENCE, EMF_METER, FLASHLIGHT, STRONG_FLASHLIGHT, UV_FLASHLIGHT);
    }
}
