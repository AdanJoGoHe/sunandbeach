package com.serex.beachandsun.items;

import com.serex.beachandsun.ItemInit;
import com.serex.beachandsun.sound.ModSounds;
import java.util.List;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class HoneyRumItem extends Item {
  public HoneyRumItem(Settings settings) {
    super(settings);
  }

  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    return ItemUsage.consumeHeldItem(world, user, hand);
  }
  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }
  @Override
  public int getMaxUseTime(ItemStack stack) {
    return 32;
  }

  @Override
  public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
    PlayerEntity playerEntity;
    PlayerEntity playerEntity2 = playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
    if (playerEntity instanceof ServerPlayerEntity) {
      Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
    }
    if (!world.isClient) {
      List<StatusEffectInstance> list = PotionUtil.getPotionEffects(stack);
      for (StatusEffectInstance statusEffectInstance : list) {
        if (statusEffectInstance.getEffectType().isInstant()) {
          statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), 1.0);
          continue;
        }
        user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
      }
    }
    if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
      playerEntity.playSound(ModSounds.QUICK_REVIVE_SOUND,100,1);
      user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1000, 1));
      user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000));
      user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1));
      stack.decrement(1);
    }
    user.emitGameEvent(GameEvent.DRINK);
    return stack;
  }
  @Override
  public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    PotionUtil.buildTooltip(stack, tooltip, 1.0f);
  }
}
