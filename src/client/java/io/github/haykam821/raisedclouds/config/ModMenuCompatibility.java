package io.github.haykam821.raisedclouds.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.gui.screens.Screen;

public class ModMenuCompatibility implements ModMenuApi {
	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return screen -> AutoConfigClient.getConfigScreen(RaisedCloudsConfig.class, screen).get();
	}
}
