package com.serex.beachandsun.items;

import com.serex.beachandsun.sound.ModSounds;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class MiracleSword extends SwordItem{

  public static final ToolMaterial MIRACLE_SWORD_MATERIAL = new ToolMaterial() {

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

  public MiracleSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed,
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
      world.playSoundFromEntity(null,user, ModSounds.VODKA_FINISH_SOUND, SoundCategory.PLAYERS,100,100);
      //serverToClientDrawParticule(world, blockPos, radio, distanceFromPlayer);
    }
    List<Entity> listEntities = world.getOtherEntities(user,
        new Box(center.getX() - radio, center.getY() - radio, center.getZ() - radio,
            center.getX() + radio, center.getY() + radio, center.getZ() + radio));

    OnEntityHitDamage(listEntities, user, this.getAttackDamage()/2, true);
    user.getItemCooldownManager().set(itemStack.getItem(), 800);
    return TypedActionResult.success(user.getStackInHand(hand));
  }

  public static void OnEntityHitDamage(List<Entity> listEntities, PlayerEntity user, float damage, boolean setOnFire)
  {
    listEntities.forEach(entity -> {
      World world = user.getWorld();
      world.playSoundFromEntity(null,entity, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,100,100);
      LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(world);
      if (lightningBolt != null) {
        lightningBolt.refreshPositionAfterTeleport(entity.getX(), entity.getY(), entity.getZ());
      }
      world.spawnEntity(lightningBolt);

      if(setOnFire) {
        entity.setOnFireFor(5);
      }


      entity.damage(world.getDamageSources().generic(), damage);
    });
  }

    /*@Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack stack) {
      return false;
    }*/
}
