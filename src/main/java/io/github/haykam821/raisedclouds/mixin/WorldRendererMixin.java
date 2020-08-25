package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import io.github.haykam821.raisedclouds.ClientMain;
import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Shadow
	private MinecraftClient client;

	@Shadow
	private ClientWorld world;

	@Unique
	private RaisedCloudsConfig CONFIG = ClientMain.getConfig();

	@Unique
	private float getBaseY() {
		return (float) ((CONFIG.overrideBaseY ? CONFIG.baseY : this.world.getSkyProperties().getCloudsHeight()) * CONFIG.scale);
	}

	@ModifyVariable(method = "renderClouds", index = 18, at = @At(value = "STORE"))
	private double getCloudY(double cloudY) {
		float baseY = this.getBaseY();
		if (CONFIG.cameraAnchor) {
			return baseY;
		} else {
			Camera camera = this.client.gameRenderer.getCamera();
			float cameraY = (float) camera.getPos().getY();

			return baseY - cameraY + 0.33f;
		}
	}
}