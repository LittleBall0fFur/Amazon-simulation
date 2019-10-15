package com.nhlstenden.amazonsimulation.physics;

public final class Vector3D {
	
	public double x, y, z;

	public Vector3D() {
		this(0, 0, 0);
	}
	
	public Vector3D(final Vector3D other) {
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
		double x = end_point.x - start_point.x;
		double y = end_point.y - start_point.y;
		double z = end_point.z - start_point.z;
		
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public void normalize() {
		this.scale(1.0/magnitude());
	}
	
	public void add(Vector3D other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
	}
	
	public void subtract(Vector3D other) {
		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
	}
	
	public void scale(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	@Override
	public String toString() {
		return "{ " + this.x +
			   ", " + this.y +
			   ", " + this.z + " }";
	}
	
}
