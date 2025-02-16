//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jozufozu.flywheel.mixin.sodium;

import com.google.common.base.Suppliers;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class SodiumMixinPlugin implements IMixinConfigPlugin {
	private static final Supplier<Boolean> IS_SODIUM_LOADED = Suppliers.memoize(() -> LoadingModList.get().getModFileById("sodium") != null);

	public SodiumMixinPlugin() {
	}

	public void onLoad(String mixinPackage) {
	}

	public String getRefMapperConfig() {
		return null;
	}

	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return (Boolean)IS_SODIUM_LOADED.get();
	}

	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	public List<String> getMixins() {
		return null;
	}

	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
}
