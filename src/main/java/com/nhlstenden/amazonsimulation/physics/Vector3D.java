package com.nhlstenden.amazonsimulation.physics;

public final class Vector3D {
	
	public final double x, y, z;

	public Vector3D() {
		this(0, 0, 0);
	}
	
	public Vector3D(Vector3D other) {
		this(other.x, other.y, other.z);
	}
	
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static double angle(Vector3D left_vector, Vector3D right_vector) {
		return Math.acos(dot(left_vector, right_vector) / (left_vector.magnitude() * right_vector.magnitude()));
	}
	
	public static double dot(Vector3D left_vector, Vector3D right_vector) {
		return (left_vector.x*right_vector.x) + (left_vector.y*right_vector.y) + (left_vector.z*right_vector.z);
	}
	
	public static double distance(Vector3D start_point, Vector3D end_point) {	
		return start_point.subtract(end_point).magnitude();
	}

	public Vector3D limit(double max_length) {
		return (this.magnitude() > max_length) ? this.normalize().scale(max_length) : this;
	}
	
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector3D normalize() {
		return this.scale(1.0/magnitude());
	}
	
	public Vector3D add(Vector3D other) {
		return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
	}
	
	public Vector3D subtract(Vector3D other) {
		return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
	}
	
	public Vector3D scale(double scalar) {
		return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		} else if (object == null || !(object instanceof Vector3D)) {
			return false;
		}
		
		Vector3D other = (Vector3D) object;
		return (Double.compare(this.x, other.x) == 0) && 
			   (Double.compare(this.y, other.y) == 0) &&
			   (Double.compare(this.z, other.z) == 0);
	}
	
	@Override
	public String toString() {
		return "{ " + this.x +
			   ", " + this.y +
			   ", " + this.z + " }";
	}
	
}
