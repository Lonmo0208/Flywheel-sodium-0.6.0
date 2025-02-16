package com.jozufozu.flywheel.backend.instancing.batching;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.jozufozu.flywheel.api.InstanceData;
import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.Material;
import com.jozufozu.flywheel.api.struct.Batched;
import com.jozufozu.flywheel.api.struct.Instanced;
import com.jozufozu.flywheel.core.model.Model;

/**
 * A collection of Instancers that all have the same format.
 * @param <D>
 */
public class BatchedMaterial<D extends InstanceData> implements Material<D> {

	protected Map<Object, CPUInstancer<D>> models = new HashMap<>();
	protected final Batched<D> type1;
	protected final Instanced<D> type;
	protected final List<CPUInstancer<D>> uninitialized = new ArrayList<>();

	public BatchedMaterial(Batched<D> type, Instanced<? extends InstanceData> type1) {
		this.type1 = type;
		this.type = (Instanced<D>) type1;
		this.models = new HashMap<>();
	}


	/**
	 * Get an instancer for the given model. Calling this method twice with the same key will return the same instancer.
	 *
	 * @param key An object that uniquely identifies the model.
	 * @param modelSupplier A factory that creates the IModel that you want to render.
	 * @return An instancer for the given model, capable of rendering many copies for little cost.
	 */
	@Override
	public Instancer<D> model(Object key, Supplier<Model> modelSupplier) {
		return models.computeIfAbsent(key, $ -> {
			CPUInstancer<D> instancer = new CPUInstancer<>(type, modelSupplier.get());
			uninitialized.add(instancer);
			return instancer;
		});
	}

	public Collection<CPUInstancer<D>> getAllInstancers() {
		return models.values();
	}

	public int getInstanceCount() {
		return models.values().stream().mapToInt(CPUInstancer::getInstanceCount).sum();
	}

	public int getVertexCount() {
		return models.values().stream().mapToInt(CPUInstancer::getVertexCount).sum();
	}

	public boolean nothingToRender() {
		return models.size() > 0 && models.values()
				.stream()
				.allMatch(CPUInstancer::isEmpty);
	}

	/**
	 * Clear all instance data without freeing resources.
	 */
	public void clear() {
		models.values()
				.forEach(CPUInstancer::clear);
	}

	public void delete() {
		models.values().forEach(CPUInstancer::delete);
		models.clear();
	}
}
