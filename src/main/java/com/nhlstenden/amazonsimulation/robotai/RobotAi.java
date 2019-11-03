package com.nhlstenden.amazonsimulation.robotai;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.domain.StorageRack;
import com.nhlstenden.amazonsimulation.pathfinding.Path;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotAi extends Robot {

	public enum RobotAiTasks{ NONE, DROP, LOAD, PICKUP, PUTDOWN, STORE}
	
	private static float ROBOT_SPEED = 0.03f;
	private static double ROBOT_TARGET_THRESHOLD = 0.1f;

	private Path path;
	private RobotAiTasks task;
	private Vector3D target;
	private StorageRack storageRack;
	
	public RobotAi(RobotController controller, Vector3D spawnPosition) {
		super(controller, spawnPosition);
		task = RobotAiTasks.NONE;
	}
	
	public void update() {
		if(target == null) {
			return;
		}
		
		// check if robot at target
		double dist = Vector3D.distance(this.getTransform().getPosition(), target);
		if(dist <= RobotAi.ROBOT_TARGET_THRESHOLD) { 
			if(!this.getTransform().getPosition().equals(target)) { 
				// set position equal to target position
				this.getTransform().setPosition(target);
				
				if(this.storageRack != null) {
					this.storageRack.getTransform().setPosition(target);
				}
			}else { 
				// set new target point
				if(path.hasNextPoint()) {
					this.target = path.getNextPoint();
				}else {
					controller.requestTask(this);
				}
			}
		}else {
			// move robot to target
			Vector3D dir = target.subtract(this.getTransform().getPosition()).normalize();
			this.getTransform().setPosition(this.getTransform().getPosition().add(dir.scale(RobotAi.ROBOT_SPEED)));
			
			if(this.storageRack != null) {
				this.storageRack.getTransform().setPosition(this.getTransform().getPosition());
			}
		}
	}
	
	public void setPath(Path path) {
		this.path = path;
		
		// set next target point
		if(path.hasNextPoint()) {
			this.target = path.getNextPoint();
		}else {
			controller.requestTask(this);
		}
	}
	
	public void setStorageRack(StorageRack storageRack) {
		this.storageRack = storageRack;
	}
	
	public StorageRack getStorageRack() {
		return this.storageRack;
	}
	
	public void setTask(RobotAiTasks task) {
		this.task = task;
	}
	
	public RobotAiTasks getTask() {
		return this.task;
	}

}
