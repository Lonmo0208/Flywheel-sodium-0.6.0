//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jozufozu.flywheel.compat;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderDispatcher;
import org.embeddedt.embeddium.api.ChunkDataBuiltEvent;

public class EmbeddiumCompat {
	public EmbeddiumCompat() {
	}

	public static void init() {
		ChunkDataBuiltEvent.BUS.addListener((event) -> event.getDataBuilder().removeBlockEntitiesIf(InstancedRenderDispatcher::tryAddBlockEntity));
	}
}
