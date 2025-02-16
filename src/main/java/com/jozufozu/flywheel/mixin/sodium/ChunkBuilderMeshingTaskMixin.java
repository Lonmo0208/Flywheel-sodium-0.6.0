package com.jozufozu.flywheel.mixin.sodium;

import com.jozufozu.flywheel.backend.instancing.InstancedRenderDispatcher;

import me.jellysquid.mods.sodium.client.render.chunk.compile.tasks.ChunkBuilderMeshingTask;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(
		value = {ChunkBuilderMeshingTask.class},
		remap = false
)
public class ChunkBuilderMeshingTaskMixin {
	public ChunkBuilderMeshingTaskMixin() {
	}

	@Redirect(
			method = {"execute(Lme/jellysquid/mods/sodium/client/render/chunk/compile/ChunkBuildContext;Lme/jellysquid/mods/sodium/client/util/task/CancellationToken;)Lme/jellysquid/mods/sodium/client/render/chunk/compile/ChunkBuildOutput;"},
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/blockentity/BlockEntityRenderDispatcher;getRenderer(Lnet/minecraft/world/level/block/entity/BlockEntity;)Lnet/minecraft/client/renderer/blockentity/BlockEntityRenderer;",
					remap = true
			)
	)
	    private BlockEntityRenderer<?> flywheel$redirectGetRenderer(BlockEntityRenderDispatcher dispatcher, BlockEntity blockEntity) {
		return InstancedRenderDispatcher.tryAddBlockEntity(blockEntity) ? null : dispatcher.getRenderer(blockEntity);
	}
}
