package com.serex.beachandsun.entity;

import com.serex.beachandsun.Constants;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

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
    return new Identifier(Constants.MODIDEF, "geo/skorpion.json");
  }


}
