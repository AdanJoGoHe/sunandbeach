package com.serex.beachandsun;

import static com.serex.beachandsun.Constants.MODIDEF;
import static com.serex.beachandsun.items.BlueChurroSword.BLUE_CHURRO_SWORD_MATERIAL;
import static com.serex.beachandsun.items.GreenChurroSword.GREEN_CHURRO_SWORD_MATERIAL;
import static com.serex.beachandsun.items.MiracleSword.MIRACLE_SWORD_MATERIAL;
import static com.serex.beachandsun.items.WoodenMace.WOODEN_MACE_MATERIAL;

import com.serex.beachandsun.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemInit {
  public static final Item TROPICAL_CAN = registerItem("tropical", new TropicalCanItem(new FabricItemSettings()));
  public static final Item EMPTY_TROPICAL_CAN = registerItem("empty_tropical", new Item(new FabricItemSettings()));
  public static final Item TIRMA = registerItem("tirma", new TirmaItem(new FabricItemSettings()));
  public static final Item GOFIO_BAG = registerItem("gofio_bag", new GofioBagItem(new FabricItemSettings()));
  public static final Item VODKA = registerItem("vodka", new VodkaItem(new FabricItemSettings()));
  public static final Item MALIBU = registerItem("malibu", new MalibuItem(new FabricItemSettings()));
  public static final Item JUGGERNOG_DRINK = registerItem("juggernog_drink", new JuggernogDrinkItem(new FabricItemSettings()));
  public static final Item REUSABLE_POTION = registerItem("reusable_potion", new ReusablePotionItem(new FabricItemSettings()));
  public static final Item HONEY_RUM = registerItem("honey_rum", new HoneyRumItem(new FabricItemSettings()));
  public static final Item MIRACLE_SWORD =  registerItem("miracle_sword", new MiracleSword(MIRACLE_SWORD_MATERIAL, 25, (float) -1.8 , new Item.Settings()));
  public static final Item WOODEN_MACE =  registerItem("wooden_mace", new WoodenMace(WOODEN_MACE_MATERIAL, 25, (float) -1.8 , new Item.Settings()));
  public static final Item GREEN_CHURRO =  registerItem("green_churro", new GreenChurroSword(GREEN_CHURRO_SWORD_MATERIAL, 3, (float) -1.8 , new Item.Settings()));
  public static final Item BLUE_CHURRO =  registerItem("blue_churro", new BlueChurroSword(BLUE_CHURRO_SWORD_MATERIAL, 3, (float) -1.8 , new Item.Settings()));

  public static final Item CANDELABRO = registerItem("candelabro", new Candelabro(new FabricItemSettings()));

  private static void addItemsToMiscItemGroup(FabricItemGroupEntries entries){
    entries.add(TROPICAL_CAN);
    entries.add(TIRMA);
    entries.add(GOFIO_BAG);
    entries.add(EMPTY_TROPICAL_CAN);
    entries.add(VODKA);
    entries.add(MIRACLE_SWORD);
    entries.add(MALIBU);
    entries.add(WOODEN_MACE);
    entries.add(GREEN_CHURRO);
    entries.add(BLUE_CHURRO);
    entries.add(HONEY_RUM);
    entries.add(REUSABLE_POTION);
    entries.add(CANDELABRO);
  }

  private static Item registerItem(String name, Item item)  {
    return Registry.register(Registries.ITEM, new Identifier(MODIDEF, name), item);
  }

  public static void registerItems(){
    BeachAndSun.LOGGER.info("Registering Mod items for {}", MODIDEF);
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemInit::addItemsToMiscItemGroup);
  }
}
