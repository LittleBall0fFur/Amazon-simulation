package com.nhlstenden.amazonsimulation.physics;

import java.util.Observable;

public abstract class Object3D extends Observable {

	private Vector3D position;
	
	private Vector3D direction;
	private double velocity;
	private double acceleration;
	
	public Object3D() {
		this.position = new Vector3D();
		
		this.direction = new Vector3D();
		this.velocity = 0.0D;
		this.acceleration = 0.0D;
	}
	
	public Object3D(Vector3D position) {
		this.position = new Vector3D(position);
	}
	
	public void update() {
		Vector3D movement = new Vector3D(this.direction);
		movement.scale(velocity);
		
		this.position.add(movement);
		
		this.velocity += this.acceleration;
	}
	
	public Vector3D getPosition() {
		return new Vector3D(position);
	}
	
	public void setPosition(Vector3D new_position) {
		this.position = new Vector3D(new_position);
	}
	
	public Vector3D getDirection() {
		return new Vector3D(direction);
	}
	
	public void setDirection(Vector3D new_direction) {
		this.direction = new Vector3D(new_direction);
		this.direction.normalize();
	}
	
	public double getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(double new_velocity) {
		this.velocity = new_velocity;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	public void setAcceleration(double new_acceleration) {
		this.acceleration = new_acceleration;
	}
	
}
