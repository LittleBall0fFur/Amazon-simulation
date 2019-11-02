package com.nhlstenden.amazonsimulation.physics;

import java.util.ArrayList;
import java.util.List;

public final class Transform {
	
	private Transform parent;
	private List<Transform> children;

	private Vector3D position;
	private Vector3D localPosition;
	
	private Vector3D rotation;
	private Vector3D localRotation;
	
	public Transform() {
		this(new Vector3D(0, 0, 0), new Vector3D(0, 0, 0));
	}
	
	public Transform(Vector3D position, Vector3D rotation) {
		this.parent = null;
		this.children = new ArrayList<Transform>();
		
		this.position = position;
		this.localPosition = position;
		
		this.rotation = rotation;
		this.localRotation = rotation;
	}
	
	public boolean hasParent() {
		return parent != null;
	}
	
	public synchronized void AttachToParent(Transform parent) {
		if (this.hasParent() == true)
			throw new IllegalStateException("");
		
		this.parent = parent;
		this.parent.children.add(this);
		
		localPosition = position.subtract(parent.position);
		localRotation = rotation.subtract(parent.rotation);
	}
	
	public synchronized void DetachFromParent() {
		if (this.hasParent() != true)
			throw new IllegalStateException("");
		
		position = parent.position.add(localPosition);
		localPosition = position;
		
		rotation = parent.rotation.add(localRotation);
		localRotation = rotation;
		
		parent.children.remove(this);
		parent = null;
	}
	
	public Vector3D getPosition() {
		return this.hasParent() ? parent.getPosition().add(localPosition) : position;
	}
	
	public void setPosition(Vector3D new_position) {
		if (this.hasParent()) {
			localPosition = new_position.subtract(parent.getPosition());
		} else {
			position = new_position;
		}
	}
	
	public Vector3D getLocalPosition() {
		return this.hasParent() ? localPosition : position;
	}

	public void setLocalPosition(Vector3D new_localPosition) {
		if (this.hasParent()) {
			localPosition = new_localPosition;
		} else {
			position = new_localPosition;
		}
	}
	
	public Vector3D getRotation() {
		return this.hasParent() ? parent.getRotation().add(localRotation) : rotation;
	}
	
	public void setRotation(Vector3D new_rotation) {
		if (this.hasParent()) {
			localRotation = new_rotation.subtract(parent.getPosition());
		} else {
			rotation = new_rotation;
		}
	}
	
	public Vector3D getLocalRotation() {
		return this.hasParent() ? localRotation : rotation;
	}

	public void setLocalRotation(Vector3D new_localRotation) {
		if (this.hasParent()) {
			localRotation = new_localRotation;
		} else {
			rotation = new_localRotation;
		}
	}
	
}
