package com.serex.beachandsun.sound;

import static com.serex.beachandsun.Constants.MODIDEF;

import com.serex.beachandsun.BeachAndSun;
import com.serex.beachandsun.Constants;
import com.serex.beachandsun.ItemInit;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
  public static SoundEvent VODKA_FINISH_SOUND = registerSoundEvent("vodka_finish_drink");
  public static SoundEvent LC_SHOP_SONG = registerSoundEvent("lc_shop_song");
  public static SoundEvent QUICK_REVIVE_SOUND = registerSoundEvent("quick_revive_sound");

  private static SoundEvent registerSoundEvent(String name){
    Identifier id = new Identifier(Constants.MODIDEF, name);
    return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
  }

  public static void registerSounds(){
    BeachAndSun.LOGGER.info("Registering Mod Sounds for {}", MODIDEF);
  }
}
