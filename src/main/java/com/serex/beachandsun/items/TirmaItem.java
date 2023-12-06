package com.serex.beachandsun.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TirmaItem extends Item {

  public TirmaItem(Settings settings) {
    super(settings.food(new FoodComponent.Builder().hunger(6).snack().alwaysEdible().statusEffect(new StatusEffectInstance(
        StatusEffects.SPEED, 200, 3), 1).build()));
  }



  @Override
  public boolean isFood() {
    return true;
  }
  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    return super.use(world, user, hand);
  }


}
