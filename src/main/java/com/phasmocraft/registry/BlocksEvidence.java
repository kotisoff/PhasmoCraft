package com.phasmocraft.registry;

import com.phasmocraft.block.evidence.SaltBlockEntity;
import com.phasmocraft.block.evidence.salt;
import com.phasmocraft.block.evidence.writing_book;
import com.phasmocraft.block.test.uvPrintsBlock;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.phasmocraft.Phasmo.GROUP_EVIDENCE;
import static com.phasmocraft.Phasmo.MODID;
import static com.phasmocraft.registry.ModRegistryUtil.addItemsToGroup;

public class BlocksEvidence {
    public static final salt SALT = new salt(FabricBlockSettings.create());
    public static final Item SALT_ITEM = createBlockItem(SALT);

    public static final BlockEntityType<SaltBlockEntity> SALT_BLOCK_ENTITY = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        new Identifier(MODID, "salt_block_entity"),
        FabricBlockEntityTypeBuilder.create(SaltBlockEntity::new, SALT).build()
    );

    public static final writing_book WRITING_BOOK = new writing_book(FabricBlockSettings.create());
    public static final Item WRITING_BOOK_ITEM = createBlockItem(WRITING_BOOK);

    public static final uvPrintsBlock UV_PRINTS_BLOCK = new uvPrintsBlock(FabricBlockSettings.create());
    public static final Item UV_PRINTS_BLOCK_ITEM = createBlockItem(UV_PRINTS_BLOCK);

    public static void RegisterBlocks(){
        RegisterBlock(new Identifier(MODID, "salt"), SALT, SALT_ITEM);
        RegisterBlock(new Identifier(MODID, "writing_book"), WRITING_BOOK, WRITING_BOOK_ITEM);
        RegisterBlock(new Identifier(MODID, "uv_prints_block"), UV_PRINTS_BLOCK, UV_PRINTS_BLOCK_ITEM);
        addItemsToGroup(GROUP_EVIDENCE, SALT_ITEM, WRITING_BOOK_ITEM, UV_PRINTS_BLOCK_ITEM);
    }

    

    private static Item createBlockItem(Block block){
        return new BlockItem(block, new Item.Settings());
    }
    private static void RegisterBlock(Identifier id, Block block, Item item){
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, item);
    }
}
