package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import io.github.haykam821.raisedclouds.ClientMain;
import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import net.minecraft.client.render.CloudRenderer;

@Mixin(CloudRenderer.class)
public class CloudRendererMixin {
	@Unique
	private static final RaisedCloudsConfig raisedclouds$CONFIG = ClientMain.getConfig();

	@ModifyConstant(method = "renderClouds(ILnet/minecraft/client/option/CloudRenderMode;FLorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/util/math/Vec3d;F)V", constant = @Constant(floatValue = 0.030000001f))
	private float getCloudSpeed(float cloudSpeed) {
		return cloudSpeed * raisedclouds$CONFIG.speed;
	}

	@ModifyConstant(method = {
		"renderClouds(ILnet/minecraft/client/option/CloudRenderMode;FLorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/util/math/Vec3d;F)V",
		"buildCloudCellFancy"
	}, constant = @Constant(floatValue = 4))
	private float getCloudHeight(float cloudHeight) {
		return raisedclouds$CONFIG.height;
	}

	@ModifyArg(method = "tessellateClouds", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/ColorHelper;fromFloats(FFFF)I"), index = 0)
	private float getCloudOpacity(float cloudOpacity) {
		return raisedclouds$CONFIG.opacity;
	}
}