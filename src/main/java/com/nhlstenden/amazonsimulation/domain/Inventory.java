package com.nhlstenden.amazonsimulation.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class Inventory {
	
	public static class Builder {
		
		private Map<Vector3D, InventorySlot> slots;
		
		public Builder() {
			this.slots = new HashMap<Vector3D, InventorySlot>();
		}
		
		public Builder addInventorySlot(Vector3D position) {
			return addInventorySlot(position, null);
		}
		
		public Builder addInventorySlot(Vector3D position, StorageRack content) {
			slots.put(position, new InventorySlot(position, content));
			return this;
		}
		
		public Inventory build() {
			return new Inventory(this);
		}
		
	}
	
	private Map<Vector3D, InventorySlot> slots;
	
	private Inventory(Builder builder) {
		this.slots = new HashMap<Vector3D, InventorySlot>(builder.slots);
	}
	
	public InventorySlot getSlotAtPosition(Vector3D position) {
		return slots.get(position);
	}
	
	public InventorySlot findOccupiedSlot() {
		return this.findSlot(true);
	}
	
	public InventorySlot findFreeSlot() {
		return this.findSlot(false);
	}
	
	private InventorySlot findSlot(boolean occupied) {
		InventorySlot result = null;
		
		final int MAX_ITERATIONS = ThreadLocalRandom.current().nextInt(1, slots.size());
		int iteration = 0;
		for (InventorySlot slot : slots.values()) {
			if (slot.isOccupied() == occupied) {
				result = slot;
				if (++iteration == MAX_ITERATIONS) break;
			}
		}
		
		return result;
	}

}
