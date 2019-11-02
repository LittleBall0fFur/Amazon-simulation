package com.nhlstenden.amazonsimulation.domain;

import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class InventorySlot {

	private Vector3D position;
	private StorageRack content;
	
	public InventorySlot(Vector3D position) {
		this(position, null);
	}
	
	public InventorySlot(Vector3D position, StorageRack storageRack) {
		this.position = position;
		this.content = storageRack;
	}
	
	public Vector3D getPosition() {
		return position;
	}
	
	public boolean isOccupied() {
		return (content != null);
	}
	
	public boolean isFree() {
		return (content == null);
	}
	
	public void placeStorageRack(StorageRack storageRack) {
		if (this.isOccupied()) {
			throw new IllegalStateException("");
		}
		
		content = storageRack;
	}
	
	public StorageRack takeStorageRack() {
		if (this.isFree()) {
			throw new IllegalStateException("");
		}
		
		StorageRack storageRack = content;
		content = null;
		
		return storageRack;
	}
	
}
