package com.nhlstenden.amazonsimulation.robotai;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.pathfinding.Path;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotAi extends Robot {
	
	private static float ROBOT_SPEED = 0.01f;
	private static double ROBOT_TARGET_THRESHOLD = 0.1f;

	private Path path;
	private Vector3D target;
	
	public RobotAi(RobotController controller, Vector3D spawnPosition) {
		super(controller, spawnPosition);
	}
	
	public void update() {
		// check if robot at target
		double dist = Vector3D.distance(this.getTransform().getPosition(), target);
		if(dist <= RobotAi.ROBOT_TARGET_THRESHOLD) { 
			if(!this.getTransform().getPosition().equals(target)) { 
				// set position equal to target position
				this.getTransform().setPosition(target);
			}else { 
				// set new target point
				if(path.hasNextPoint()) {
					this.target = path.getNextPoint();
				}else {
					controller.help(this);
				}
			}
		}else {
			// move robot to target
			Vector3D dir = target.subtract(this.getTransform().getPosition());
			this.getTransform().setPosition(this.getTransform().getPosition().add(dir.scale(RobotAi.ROBOT_SPEED)));
		}
	}
	
	public void setPath(Path path) {
		this.path = path;
		
		// set next target point
		if(path.hasNextPoint()) {
			this.target = path.getNextPoint();
		}else {
			controller.help(this);
		}
	}

}
