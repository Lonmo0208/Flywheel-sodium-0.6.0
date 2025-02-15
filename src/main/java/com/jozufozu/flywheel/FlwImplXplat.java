package com.jozufozu.flywheel;

import java.util.function.BooleanSupplier;

import com.jozufozu.flywheel.config.FlwConfig;

import  com.jozufozu.flywheel.api.internal.DependencyInjection;
import net.minecraft.client.multiplayer.ClientLevel;

public interface FlwImplXplat {
	FlwImplXplat INSTANCE = DependencyInjection.load(FlwImplXplat.class, "dev.engine_room.flywheel.impl.FlwImplXplatImpl");

	void dispatchReloadLevelRendererEvent(ClientLevel level);

	String getVersionStr();

	FlwConfig getConfig();

	BooleanSupplier getModLoaded(String modId);
}
