package com.serex.beachandsun.entity;

import com.serex.beachandsun.Constants;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SkorpionModel extends GeoModel<SkorpionEntity> {

  @Override
  public Identifier getModelResource(SkorpionEntity animatable) {
    return new Identifier(Constants.MODIDEF, "geo/skorpion.json");
  }

  @Override
  public Identifier getTextureResource(SkorpionEntity animatable) {
    return new Identifier(Constants.MODIDEF, "textures/entity/skorpion.png");
  }

  @Override
  public Identifier getAnimationResource(SkorpionEntity animatable) {
    return new Identifier(Constants.MODIDEF, "animations/skorpion.animation.json");
  }

  @Override
  public void setCustomAnimations(SkorpionEntity animatable, long instanceId,
                                  AnimationState<SkorpionEntity> animationState) {
    CoreGeoBone head = getAnimationProcessor().getBone("head");

    if(head != null){
      EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
      head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
      head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
    }
  }
}
