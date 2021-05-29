package io.github.haykam821.raisedclouds.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "raisedclouds")
@Config.Gui.Background("minecraft:textures/block/white_wool.png")
public class RaisedCloudsConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip(count = 3)
	public boolean overrideBaseY = false;

	@ConfigEntry.Gui.Tooltip
	public float baseY = 128;

	@ConfigEntry.Gui.Tooltip
	public double scale = 1;

	@ConfigEntry.Gui.Tooltip(count = 2)
	public boolean cameraAnchor = false;
}