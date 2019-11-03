package com.nhlstenden.amazonsimulation.domain;

import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class InventorySlot {

	protected Vector3D position;
	protected StorageRack content;
	protected boolean reserved;
	
	public InventorySlot(Vector3D position) {
		this(position, null);
	}
	
	public InventorySlot(Vector3D position, StorageRack storageRack) {
		this.position = position;
		this.content = storageRack;
		this.reserved = false;
	}
	
	public Vector3D getPosition() {
		return position;
	}
	
	public void reserve() {
		this.reserved = true;
	}
	
	public boolean isReserved() {
		return this.reserved;
	}
	
	public boolean isOccupied() {
		return (this.content != null);
	}
	
	public boolean isFree() {
		return (this.content == null);
	}
	
	public void placeStorageRack(StorageRack storageRack) {
		if (this.isOccupied()) {
			throw new IllegalStateException("");
		}
		
		this.content = storageRack;
	}
	
	public StorageRack takeStorageRack() {
		if (this.isFree()) {
			throw new IllegalStateException("");
		}
		
		StorageRack storageRack = this.content;
		this.content = null;
		this.reserved = false;
		
		return storageRack;
	}
	
}
