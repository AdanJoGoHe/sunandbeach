package com.serex.beachandsun.items;

import com.serex.beachandsun.ItemInit;
import java.util.List;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class TropicalCanItem extends Item {
  public TropicalCanItem(Settings settings) {
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
    if (playerEntity != null) {
      playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
      if (!playerEntity.getAbilities().creativeMode) {
        stack.decrement(1);
      }
    }
    if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
      if (stack.isEmpty()) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 100, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 3000, 1));
        return new ItemStack(Items.GLASS);
      }
      if (playerEntity != null) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 2));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1000, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1000, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000 ));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 3000, 1));
        playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS));
      }
    }
    user.emitGameEvent(GameEvent.DRINK);
    return stack;
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    PotionUtil.buildTooltip(stack, tooltip, 1.0f);
  }
}
