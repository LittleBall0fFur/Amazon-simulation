package com.nhlstenden.amazonsimulation.models;

import java.util.Random;

import com.nhlstenden.amazonsimulation.physics.Object3D;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class Truck extends Object3D {
	int numGoods = 0;
	
	public Truck (Vector3D _position) {
		this.setPosition(_position);
		Arrive();
	}
	
	private int GetRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	private void Arrive() {
		numGoods = GetRandom(5, 10);
		//MoveTowards(this.getPosition(), new Vector3D(2, 2, 0));
	}
	
	public static Vector3D MoveTowards(Vector3D current, Vector3D target, float maxDistanceDelta)
    {
        double toVector_x = target.x - current.x;
        double toVector_y = target.y - current.y;
        double toVector_z = target.z - current.z;

        double sqdist = toVector_x * toVector_x + toVector_y * toVector_y + toVector_z * toVector_z;

        if (sqdist == 0 || (maxDistanceDelta >= 0 && sqdist <= maxDistanceDelta * maxDistanceDelta))
            return target;
        double dist = (float)Math.sqrt(sqdist);

        return new Vector3D(current.x + toVector_x / dist * maxDistanceDelta,
            current.y + toVector_y / dist * maxDistanceDelta,
            current.z + toVector_z / dist * maxDistanceDelta);
    }
}
