package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import io.github.haykam821.raisedclouds.ClientMain;
import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import net.minecraft.client.renderer.CloudRenderer;

@Mixin(CloudRenderer.class)
public class WorldRendererMixin {
	@Unique
	private static final RaisedCloudsConfig raisedclouds$CONFIG = ClientMain.getConfig();

	@ModifyVariable(method = "render", ordinal = 0, argsOnly = true, at = @At("HEAD"))
	private float getCloudY(float cloudY) {
		return raisedclouds$CONFIG.getCloudY();
	}
}
