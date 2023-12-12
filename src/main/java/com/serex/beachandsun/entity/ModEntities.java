package com.serex.beachandsun.entity;

import static com.serex.beachandsun.Constants.MODIDEF;

import com.serex.beachandsun.BeachAndSun;
import com.serex.beachandsun.ItemInit;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

  public static final EntityType<SkorpionEntity> SKORPION = registerItem("skorpion",
      FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SkorpionEntity::new)
          .dimensions(EntityDimensions.fixed(1.9F, 1.6F)).build());

  private static <E extends Entity> EntityType<E> registerItem(String name, EntityType<E> item)  {
    return Registry.register(Registries.ENTITY_TYPE, new Identifier(MODIDEF, name), item);
  }

  public static void registerItems(){
    BeachAndSun.LOGGER.info("Registering Mod items for {}", MODIDEF);
    //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemInit::addItemsToMiscItemGroup);
  }
}
