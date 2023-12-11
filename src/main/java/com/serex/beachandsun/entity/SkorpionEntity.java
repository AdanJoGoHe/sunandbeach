package com.serex.beachandsun.entity;

import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AttackWithOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
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

public class SkorpionEntity extends AnimalEntity implements GeoEntity {

  private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

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

    //geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.skorpion.idle", Animation.LoopType.LOOP));
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
        .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
        .add(EntityAttributes.GENERIC_ATTACK_SPEED, 4.0D)
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D)
        .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 1D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D);
  }

  @Override
  protected void initGoals() {
    this.goalSelector.add(1, new SwimGoal(this));
    this.goalSelector.add(2, new FollowParentGoal(this, 1.1D));
    this.goalSelector.add(3, new WanderAroundGoal(this, 1.0D));
    this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
  }
}