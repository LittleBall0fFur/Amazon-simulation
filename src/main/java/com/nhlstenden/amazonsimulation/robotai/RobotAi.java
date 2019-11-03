package com.nhlstenden.amazonsimulation.robotai;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotAi extends Robot {
	
	private static float ROBOT_SPEED = 0.001f;
	private static double ROBOT_TARGET_THRESHOLD = 0.1f;
	
	private Vector3D target;
	
	public RobotAi(RobotController controller, Vector3D spawnPosition) {
		super(controller, spawnPosition);
	}
	
	public void update() {
		double dist = Vector3D.distance(this.getTransform().getPosition(), target);
		if(dist <= RobotAi.ROBOT_TARGET_THRESHOLD) {
			if(!this.getTransform().getPosition().equals(target)) {
				this.getTransform().setPosition(target);
				this.controller.help(this);
			}
		}else {
			Vector3D dir = target.subtract(this.getTransform().getPosition());
			this.getTransform().setPosition(this.getTransform().getPosition().add(dir.scale(RobotAi.ROBOT_SPEED)));
		}
	}
	
	public void setTarget(Vector3D target) {
		this.target = target;
	}

}
