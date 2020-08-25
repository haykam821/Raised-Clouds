package io.github.haykam821.raisedclouds;

import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class ClientMain implements ClientModInitializer {
	private static final RaisedCloudsConfig CONFIG = AutoConfig.register(RaisedCloudsConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitializeClient() {
		return;
	}

	public static RaisedCloudsConfig getConfig() {
		return CONFIG;
	}
}