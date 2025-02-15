package com.jozufozu.flywheel.compat;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraftforge.fml.loading.LoadingModList;

public class CompatHelper {
	public static boolean IS_SODIUM_0_6 = false;
	public static final Supplier<Boolean> IS_SODIUM_LOADED = Suppliers.memoize(() -> LoadingModList.get().getModFileById("sodium") != null);
	public static final Supplier<Boolean> IS_EMBEDDIUM_LOADED = Suppliers.memoize(() -> LoadingModList.get().getModFileById("embeddium") != null);
	public static void init() {
		if (CompatMods.SODIUM.isLoaded() && !(CompatMods.RUBIDIUM.isLoaded() && CompatMods.EMBEDDIUM.isLoaded())) {
			CompatHelper.IS_SODIUM_0_6 = true;
			}
		}
	}

