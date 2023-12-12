package com.serex.beachandsun.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SkorpionEntity extends AnimalEntity implements GeoEntity {

  private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

  public SkorpionEntity(EntityType<? extends AnimalEntity> entityType,
                        World world) {
    super(entityType, world);
  }
  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0 , this::predicate));
  }

  private PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {

    if(geoAnimatableAnimationState.isMoving()){
      //geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.skorpion.movement", Animation.LoopType.LOOP));
    }
    geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.skorpion.idle", Animation.LoopType.LOOP));
    return PlayState.CONTINUE;
  }


  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return cache;
  }

  @Nullable
  @Override
  public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
    return ModEntities.SKORPION.create(world);
  }

  public static DefaultAttributeContainer.Builder setAttributes() {
    return DefaultAttributeContainer.builder()
        .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D)
        .add(EntityAttributes.GENERIC_ATTACK_SPEED, 3.0D)
        .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D)
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4D)
        .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 0D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 8D)
        .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 1D)
        .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.55D)
        .add(EntityAttributes.GENERIC_ARMOR, 10D);
  }

  @Override
  protected void initGoals() {
    this.goalSelector.add(1, new AttackGoal(this));
    this.targetSelector.add(1, new RevengeGoal(this));
    this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 60f));
    this.goalSelector.add(2, new SwimGoal(this));
    this.goalSelector.add(2, new FollowParentGoal(this, 1.1D));
    this.goalSelector.add(3, new WanderAroundGoal(this, 1.0D));

  }
}
