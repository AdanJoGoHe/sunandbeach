package com.serex.beachandsun.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class BlueChurroSword extends SwordItem{

  public static final ToolMaterial BLUE_CHURRO_SWORD_MATERIAL = new ToolMaterial() {

    @Override
    public int getDurability() {
      return 2400;
    }

    @Override
    public float getMiningSpeedMultiplier() {
      return 4F;
    }

    @Override
    public float getAttackDamage() {
      return 12F;
    }

    @Override
    public int getMiningLevel() {
      return 1;
    }

    @Override
    public int getEnchantability() {
      return 2;
    }

    @Override
    public Ingredient getRepairIngredient() {
      return Ingredient.EMPTY;
    }


  };

  public BlueChurroSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed,
                         Settings settings) {
    super(toolMaterial, attackDamage, attackSpeed, settings);
  }

    /*@Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                              TooltipContext context) {
      tooltip.add(new TranslatableText("text.eriagacha.tavinus_sword_description_0"));
    }*/



  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    if (!world.isClient) {
      Vec3d lookVec = user.getRotationVec(1.0F);
      Vec3d dashVec = lookVec.multiply(1.0, 0.4, 1.0).normalize().multiply(4); // Ajusta el multiplicador para cambiar la distancia del dash
      user.addVelocity(dashVec.x, dashVec.y, dashVec.z);

      user.velocityModified = true;

      ItemStack itemStack = user.getStackInHand(hand);
      user.getItemCooldownManager().set(this, 20); // Añade un cooldown para evitar el spam
    }
    return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
  }

  @Override
  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    super.postHit(stack, target, attacker);

    World world = attacker.getWorld();
    if (!world.isClient()) {
      for (int i = 0; i < 20; i++) {
        double speedX = world.random.nextGaussian() * 0.2;
        double speedY = 0.5;
        double speedZ = world.random.nextGaussian() * 0.2;

        world.addParticle(ParticleTypes.SPLASH,
            target.getX(), target.getY() + 1.0, target.getZ(),
            speedX, speedY, speedZ);
      }
    }

    double knockbackStrength = 25;
    target.takeKnockback(knockbackStrength, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
    return true;
  }

  /*@Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack stack) {
      return false;
    }*/
}
