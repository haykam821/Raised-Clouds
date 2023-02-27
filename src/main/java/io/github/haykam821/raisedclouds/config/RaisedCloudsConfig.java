package io.github.haykam821.raisedclouds.config;

import io.github.haykam821.raisedclouds.mixin.OverworldDimensionEffectsAccessor;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;

@Config(name = "raisedclouds")
@Config.Gui.Background("minecraft:textures/block/white_wool.png")
public class RaisedCloudsConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip(count = 3)
	public boolean overrideBaseY = false;

	@ConfigEntry.Gui.Tooltip
	public float baseY = OverworldDimensionEffectsAccessor.getCloudsHeight();

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

	private float getBaseY(ClientWorld world) {
		return (float) ((this.overrideBaseY ? this.baseY : world.getDimensionEffects().getCloudsHeight()) * this.scale);
	}

	public double getCloudY(MinecraftClient client, ClientWorld world, double cloudY) {
		float baseY = this.getBaseY(world);
		if (this.cameraAnchor) {
			return baseY;
		} else {
			Camera camera = client.gameRenderer.getCamera();
			float cameraY = (float) camera.getPos().getY();

			return baseY - cameraY + 0.33f;
		}
	}

	@Override
	public void validatePostLoad() throws ValidationException {
		this.opacity = MathHelper.clamp(this.opacity, 0, 1);

		MinecraftClient client = MinecraftClient.getInstance();

		if (client.worldRenderer != null) {
			client.worldRenderer.scheduleTerrainUpdate();
		}
	}
}