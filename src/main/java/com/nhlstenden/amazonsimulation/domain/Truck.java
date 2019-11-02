package com.nhlstenden.amazonsimulation.domain;

import java.util.LinkedList;
import java.util.List;

import com.nhlstenden.amazonsimulation.physics.Object3D;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class Truck extends Object3D {
	
	private static final int STORAGE_CAPACITY = 5;
	
	private Warehouse warehouse;
	private LinkedList<StorageRack> cargo;
	
	public Truck(Warehouse warehouse, List<StorageRack> initial_cargo) {
		super();
		
		this.warehouse = warehouse;
		this.cargo = new LinkedList<StorageRack>(initial_cargo);
	}
	
	public void update() {
		
	}
	
	public boolean isFull() {
		return (cargo.size() == STORAGE_CAPACITY);
	}
	
	public boolean isEmpty() {
		return (cargo.isEmpty());
	}
	
	public void load(StorageRack storageRack) {
		if (this.isFull()) {
			throw new IllegalStateException("");
		}
		
		cargo.push(storageRack);
		cargo.peek().getTransform().AttachToParent(this.getTransform());
		cargo.peek().getTransform().setLocalPosition(new Vector3D(0, 0, 0));
	}
	
	public StorageRack unload() {
		if (this.isEmpty()) {
			throw new IllegalStateException("");
		}
		
		cargo.peek().getTransform().DetachFromParent();
		return cargo.pop();
	}
	
}
