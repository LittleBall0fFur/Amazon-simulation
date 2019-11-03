package com.nhlstenden.amazonsimulation.robotai;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotVision {
	
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
				System.out.println("in");
				n.setType(type);
			}else {
				System.out.println("out");
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

	private static class Node {
		
		private Vector3D position;
		private NodeType type;
		
		private Node(Vector3D position, NodeType type) {
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
	
	public Vector3D getStorageSpacePoint() {
		return new Vector3D(0,0,0);
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
