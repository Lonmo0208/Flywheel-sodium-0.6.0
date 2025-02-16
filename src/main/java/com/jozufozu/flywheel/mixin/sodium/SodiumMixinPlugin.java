package com.jozufozu.flywheel.mixin.sodium;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import com.jozufozu.flywheel.compat.CompatMods;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.google.common.base.Suppliers;

import net.minecraftforge.fml.loading.LoadingModList;

public class SodiumMixinPlugin implements IMixinConfigPlugin {
	public static boolean IS_SODIUM_0_6 = false;
	public static final Supplier<Boolean> IS_SODIUM_LOADED = Suppliers.memoize(() -> LoadingModList.get().getModFileById("sodium") != null);
	public static final Supplier<Boolean> IS_EMBEDDIUM_LOADED = Suppliers.memoize(() -> LoadingModList.get().getModFileById("embeddium") != null);

	public static void init() {
		if (CompatMods.SODIUM.isLoaded() && !(CompatMods.RUBIDIUM.isLoaded() && CompatMods.EMBEDDIUM.isLoaded())) {
			SodiumMixinPlugin.IS_SODIUM_0_6 = true;
		}
	}


	@Override
	public void onLoad(String mixinPackage) {
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return IS_SODIUM_LOADED.get();
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
}
