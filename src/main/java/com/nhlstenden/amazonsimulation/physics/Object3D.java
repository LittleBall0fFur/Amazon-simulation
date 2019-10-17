package com.nhlstenden.amazonsimulation.physics;

public abstract class Object3D {

	private Vector3D position;
	
	private Vector3D velocity;
	private Vector3D acceleration;
	
	public Object3D() {
		this.position = new Vector3D();
		
		this.velocity = new Vector3D();
		this.acceleration = new Vector3D();
	}
	
	public Object3D(Vector3D position) {
		this.position = new Vector3D(position);
	}
	
	public void update() {
		this.position.add(velocity);
		this.velocity.add(acceleration);
	}
	
	public Vector3D getPosition() {
		return new Vector3D(position);
	}
	
	public void setPosition(Vector3D new_position) {
		this.position = new Vector3D(new_position);
	}

	public Vector3D getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(Vector3D new_velocity) {
		this.velocity = new_velocity;
	}
	
	public Vector3D getAcceleration() {
		return this.acceleration;
	}
	
	public void setAcceleration(Vector3D new_acceleration) {
		this.acceleration = new_acceleration;
	}
	
}
