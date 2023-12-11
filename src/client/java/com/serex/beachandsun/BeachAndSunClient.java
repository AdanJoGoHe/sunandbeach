package com.serex.beachandsun;

import com.serex.beachandsun.entity.ModEntities;
import com.serex.beachandsun.entity.SkorpionModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BeachAndSunClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.SKORPION, ctx -> new SkorpionRenderer(ctx, new SkorpionModel()));
	}
}