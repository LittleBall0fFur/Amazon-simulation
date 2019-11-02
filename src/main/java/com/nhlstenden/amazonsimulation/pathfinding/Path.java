package com.nhlstenden.amazonsimulation.pathfinding;

import java.util.LinkedList;

import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class Path {

	public static class Builder {
		
		private LinkedList<Vector3D> points;
		
		public Builder() {
			this.points = new LinkedList<Vector3D>();
		}
		
		public Builder addPoint(Vector3D point) {
			points.push(point);
			return this;
		}
		
		public Path build() {
			return new Path(this);
		}
		
	}
	
	private LinkedList<Vector3D> points;
	
	private Path(Builder builder) {
		this.points = new LinkedList<Vector3D>(builder.points);
	}
	
	public boolean hasNextPoint() {
		return !this.points.isEmpty();
	}
	
	public Vector3D getNextPoint() {
		if (this.hasNextPoint() != true) {
			throw new IllegalStateException("");
		}
		
		return points.pop();
	}
	
}
