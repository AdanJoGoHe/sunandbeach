package com.serex.beachandsun;

import com.serex.beachandsun.Constants;
import com.serex.beachandsun.entity.SkorpionEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkorpionRenderer extends GeoEntityRenderer<SkorpionEntity> {
  public SkorpionRenderer(
      net.minecraft.client.render.entity.EntityRendererFactory.Context renderManager, GeoModel<SkorpionEntity> model) {
    super(renderManager, model);
  }

  @Override
  public Identifier getTextureLocation(SkorpionEntity animatable) {
    return new Identifier(Constants.MODIDEF, "textures/entity/skorpion.png");
  }

  @Override
  public void render(SkorpionEntity entity, float entityYaw, float partialTick,
                     MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
    if(entity.isBaby()){
      poseStack.scale(0.4f,0.4f, 0.4f);
    }
    super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
  }
}
