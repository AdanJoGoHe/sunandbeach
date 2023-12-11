package com.serex.beachandsun;

import com.serex.beachandsun.entity.ModEntities;
import com.serex.beachandsun.entity.SkorpionEntity;
import com.serex.beachandsun.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeachAndSun implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("beachandsun");

	@Override
	public void onInitialize() {
		ItemInit.registerItems();
		ModSounds.registerSounds();
		ModEntities.registerItems();
		FabricDefaultAttributeRegistry.register(ModEntities.SKORPION, SkorpionEntity.setAttributes());
		// for registering an entity renderer

		LOGGER.info("Hello Fabric world!");
	}
}