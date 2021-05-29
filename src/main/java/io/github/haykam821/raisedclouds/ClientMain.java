package io.github.haykam821.raisedclouds;

import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
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