package io.github.haykam821.raisedclouds.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

@Config(name = "raisedclouds")
@Config.Gui.Background("minecraft:textures/block/white_wool.png")
public class RaisedCloudsConfig implements ConfigData {

	@ConfigEntry.Gui.Tooltip(count = 3)
	public boolean overrideBaseY = false;

	@ConfigEntry.Gui.Tooltip
	public float baseY = 192.0F;

	@ConfigEntry.Gui.Tooltip
	public double scale = 1;

	@ConfigEntry.Gui.Tooltip(count = 2)
	public boolean cameraAnchor = false;

	@ConfigEntry.Gui.Tooltip
	public float height = 4;

	@ConfigEntry.Gui.Tooltip(count = 2)
	public float speed = 1;

	@ConfigEntry.Gui.Tooltip
	public float opacity = 0.8f;

	public float getCloudY() {
		float baseY = (float) (this.baseY * this.scale);
		if (this.cameraAnchor) {
			Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
			return baseY + (float) camera.position().y;
		} else {
			return baseY + 0.33f;
		}
	}

	@Override
	public void validatePostLoad() throws ValidationException {
		this.opacity = Mth.clamp(this.opacity, 0, 1);

		Minecraft minecraft = Minecraft.getInstance();

		if (minecraft.levelRenderer != null) {
			minecraft.levelRenderer.allChanged();
		}
	}
}
