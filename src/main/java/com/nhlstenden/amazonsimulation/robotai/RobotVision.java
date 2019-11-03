package com.nhlstenden.amazonsimulation.robotai;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nhlstenden.amazonsimulation.domain.InventorySlot;
import com.nhlstenden.amazonsimulation.domain.StorageRack;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotVision {
	
	private static int MAX_STORAGESPACE_X = 3;
	
	public enum NodeType{
		STORAGE_SPACE, TRUCK_PICKUP_POINT, TRUCK_DROPOFF_POINT
	}
	
	public static class Builder {

		private HashMap<Vector3D, Node> nodes;
		
		public Builder() {
			this.nodes = new HashMap<Vector3D, Node>();
		}
		
		public Builder addVertex(Vector3D vertex) {
			nodes.put(vertex, new Node(vertex, NodeType.STORAGE_SPACE));
			return this;
		}
		
		public Builder setVertexState(Vector3D vertex, NodeType type) {
			Node n = nodes.get(vertex);
			if(n != null) {
				n.setType(type);
			}
			return this;
		}
		
		public void createGrid(int _x, int _y) {
			for(int i = 0; i < _y; i++) {
				for(int j = 0; j < _x; j++) {
					this.addVertex(new Vector3D(j, i, 0));
				}
			}
		}
		
		public RobotVision build() {
			return new RobotVision(this);
		}
		
	}

	private static class Node extends InventorySlot {
		
		private NodeType type;
		
		private Node(Vector3D position, NodeType type) {
			super(position);
			this.position = position;
			this.type = type;
		}
		
		public Vector3D getPosition() {
			return this.position;
		}
		
		public void setType(NodeType type) {
			this.type = type;
		}
		
		public NodeType getType() {
			return this.type;
		}
		
	}

	private HashMap<Vector3D, Node> nodes;
	
	private RobotVision(Builder builder) {
		nodes = builder.nodes;
	}
	
	public void placeStorageRack(Vector3D position, StorageRack storageRack) {
		Node node = nodes.get(position);
		if(node != null) {
			node.placeStorageRack(storageRack);
		}
	}
	
	public StorageRack takeStorageRack(Vector3D position) {
		Node node = nodes.get(position);
		if(node != null) {
			return node.takeStorageRack();
		}
		return null;
	}
	
	public Vector3D getStorageSpaceDropPoint() {
		for(int i = 0; i <= RobotVision.MAX_STORAGESPACE_X; i++) {
			for(int j = RobotController.GRID_SIZE_Y-1; j >= 0; j--) {
				Node node = nodes.get(new Vector3D(i, j, 0));
				if(node != null && !node.isOccupied() && !node.isReserved()) {
					node.reserve();
					return node.getPosition();
				}
			}
		}
		return null;
	}
	
	public Vector3D getStorageSpacePickupPoint() {
		for(int i = RobotVision.MAX_STORAGESPACE_X-1; i >= 0 ; i--) {
			for(int j = 0; j < RobotController.GRID_SIZE_Y; j++) {
				Node node = nodes.get(new Vector3D(i, j, 0));
				if(node != null && node.isOccupied() && !node.isReserved()) {
					node.reserve();
					return node.getPosition();
				}
			}
		}
		return null;
	}
	
	public Vector3D getTruckPickupPoint() {
		for (Node node : nodes.values()) {
			if(node.getType() == NodeType.TRUCK_PICKUP_POINT) {
				return node.getPosition();
			}
		}
		return null;
	}
	
	public Vector3D getTruckDropoffPoint() {
		for (Node node : nodes.values()) {
			if(node.getType() == NodeType.TRUCK_DROPOFF_POINT) {
				return node.getPosition();
			}
		}
		return null;
	}
	
}
