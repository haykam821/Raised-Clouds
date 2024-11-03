package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import io.github.haykam821.raisedclouds.ClientMain;
import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Shadow
	private MinecraftClient client;

	@Shadow
	private ClientWorld world;

	@Unique
	private static final RaisedCloudsConfig raisedclouds$CONFIG = ClientMain.getConfig();

	@ModifyVariable(method = "renderClouds", ordinal = 1, argsOnly = true, at = @At("HEAD"))
	private float getCloudY(float cloudY) {
		return raisedclouds$CONFIG.getCloudY(this.client, this.world);
	}
}