package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.DimensionEffects;

@Mixin(DimensionEffects.Overworld.class)
public interface OverworldDimensionEffectsAccessor {
	@Accessor("CLOUDS_HEIGHT")
	public static int getCloudsHeight() {
		throw new IllegalStateException();
	}
}
