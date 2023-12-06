package com.serex.beachandsun.items;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GreenChurroSword extends SwordItem{

  public static final ToolMaterial GREEN_CHURRO_SWORD_MATERIAL = new ToolMaterial() {

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

  public GreenChurroSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed,
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
    int radio = 5;
    int distanceFromPlayer = 5 ;
    ItemStack itemStack = user.getStackInHand(hand);
    if (user.getItemCooldownManager().isCoolingDown(itemStack.getItem())) {
      return TypedActionResult.fail(itemStack);
    }
    Vec3d center = user.getPos().add(user.getRotationVector().normalize().multiply(distanceFromPlayer));
    BlockPos blockPos = new BlockPos((int)Math.floor(center.getX()), (int) Math.floor(center.getY()),(int) Math.floor(center.getZ()));
    if (!world.isClient()) {
      //world.playSoundFromEntity(null,user, ModSounds.VODKA_FINISH_SOUND, SoundCategory.PLAYERS,100,100);
      user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 2));
    }
    user.getInventory().getMaxCountPerStack();
    int particleCount = 60;
    int radius = 5;
    if (world.isClient()){
      for (int i = 0; i < particleCount; i++) {
        double angle = 2 * Math.PI * i / particleCount;
        double speedX = Math.cos(angle) * radius;
        double speedZ = Math.sin(angle) * radius;

        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME,
            user.getX(), user.getY() + 0.3, user.getZ(),
            speedX, 0.1, speedZ);
        world.addParticle(ParticleTypes.CHERRY_LEAVES,
            user.getX(), user.getY()+ 0.3, user.getZ(),
            speedX, 0.1, speedZ);
      }
    }
    user.getItemCooldownManager().set(itemStack.getItem(), 5);
    return TypedActionResult.success(user.getStackInHand(hand));
  }

    /*@Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack stack) {
      return false;
    }*/
}
