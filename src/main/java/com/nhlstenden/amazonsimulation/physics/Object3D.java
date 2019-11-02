package com.nhlstenden.amazonsimulation.physics;

import java.util.UUID;

import com.nhlstenden.amazonsimulation.identity.Identifiable;

public abstract class Object3D implements Identifiable {

	private final UUID identifier = UUID.randomUUID();
	
	private final Transform transform;
	
	protected Object3D() {
		this(new Vector3D(), new Vector3D());
	}
	
	protected Object3D(Vector3D position) {
		this(position, new Vector3D());
	}
	
	protected Object3D(final Object3D other) {
		this(other.transform.getPosition(), other.transform.getRotation());
	}
	
	protected Object3D(Vector3D position, Vector3D rotation) {
		this.transform = new Transform(position, rotation);
	}
	
	public Transform getTransform() {
		return transform;
	}
	
	@Override
	public final UUID getIdentifier() {
		return this.identifier;
	}
	
}
