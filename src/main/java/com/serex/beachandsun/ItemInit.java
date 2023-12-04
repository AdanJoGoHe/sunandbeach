package com.serex.beachandsun;

import com.serex.beachandsun.items.TropicalCanItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class ItemInit {
  public static final Item TROPICAL_CAN = new TropicalCanItem(new FabricItemSettings());

  public static void init(){
    //Registry.register(Registry.register(), new Identifier(MODIDEF, "soda_can"), SODA_CAN);
  }
}
