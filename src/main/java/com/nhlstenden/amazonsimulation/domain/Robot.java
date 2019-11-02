package com.nhlstenden.amazonsimulation.domain;

import com.nhlstenden.amazonsimulation.physics.Object3D;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class Robot extends Object3D {

	private Warehouse warehouse;
	private StorageRack cargo;
	
	public Robot(Warehouse warehouse) {
		super();
		
		this.warehouse = warehouse;
		this.cargo = null;
	}
	
	public boolean isLoaded() {
		return (cargo != null);
	}
	
	public boolean isEmpty() {
		return (cargo == null);
	}
	
	public void load(StorageRack storageRack) {
		if (this.isLoaded()) {
			throw new IllegalStateException("");
		}

		cargo = storageRack;
		cargo.getTransform().AttachToParent(this.getTransform());
		cargo.getTransform().setLocalPosition(new Vector3D(0, 0, 0));
	}
	
	public StorageRack unload() {
		if (this.isEmpty()) {
			throw new IllegalStateException("");
		}

		cargo.getTransform().DetachFromParent();
		return cargo;
	}
	
}
