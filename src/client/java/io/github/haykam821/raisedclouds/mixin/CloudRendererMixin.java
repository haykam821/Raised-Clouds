package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import io.github.haykam821.raisedclouds.ClientMain;
import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.util.ARGB;

@Mixin(CloudRenderer.class)
public class CloudRendererMixin {
	@Unique
	private static final RaisedCloudsConfig raisedclouds$CONFIG = ClientMain.getConfig();

	@ModifyConstant(method = "render", constant = @Constant(floatValue = 0.030000001f))
	private float getCloudSpeed(float cloudSpeed) {
		return cloudSpeed * raisedclouds$CONFIG.speed;
	}

	@ModifyConstant(method = "render", constant = @Constant(floatValue = 4.0f, ordinal = 1))
	private float getCloudHeight(float cloudHeight) {
		return raisedclouds$CONFIG.height;
	}

	@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;vector4fFromARGB32(I)Lorg/joml/Vector4f;"))
	private int getCloudOpacity(int color) {
		int alpha = (int) (ARGB.alpha(color) * raisedclouds$CONFIG.opacity);
		return ARGB.color(alpha, ARGB.red(color), ARGB.green(color), ARGB.blue(color));
	}
}
