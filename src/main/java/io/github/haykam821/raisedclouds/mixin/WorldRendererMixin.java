package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
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

	@ModifyVariable(method = "renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FDDD)V", ordinal = 6, at = @At("STORE"))
	private double getCloudY(double cloudY) {
		return raisedclouds$CONFIG.getCloudY(this.client, this.world, cloudY);
	}

	@ModifyConstant(method = "renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FDDD)V", constant = @Constant(floatValue = 0.03f))
	private float getCloudSpeed(float cloudSpeed) {
		return cloudSpeed * raisedclouds$CONFIG.speed;
	}

	@ModifyConstant(method = {
		"renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FDDD)V",
		"renderClouds(Lnet/minecraft/client/render/BufferBuilder;DDDLnet/minecraft/util/math/Vec3d;)Lnet/minecraft/client/render/BufferBuilder$BuiltBuffer;"
	}, constant = @Constant(floatValue = 4))
	private float getCloudHeight(float cloudHeight) {
		return raisedclouds$CONFIG.height;
	}

	@ModifyConstant(method = {
		"renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FDDD)V",
		"renderClouds(Lnet/minecraft/client/render/BufferBuilder;DDDLnet/minecraft/util/math/Vec3d;)Lnet/minecraft/client/render/BufferBuilder$BuiltBuffer;"
	}, constant = @Constant(doubleValue = 4))
	private double getCloudHeight(double cloudHeight) {
		return (double) raisedclouds$CONFIG.height;
	}
}