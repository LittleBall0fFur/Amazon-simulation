package com.nhlstenden.amazonsimulation.robotai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.domain.StorageRack;
import com.nhlstenden.amazonsimulation.pathfinding.Map;
import com.nhlstenden.amazonsimulation.pathfinding.Path;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotController {
	
	public enum RobotControllerTasks { UNLOAD, LOAD };
	
	private static int ROBOT_START_AMOUNT = 2;
	private static Vector3D GRID_SPAWN_POINT = new Vector3D(4, 2, 0);
	public static int GRID_SIZE_X = 8;
	public static int GRID_SIZE_Y = 5;
	
	private Map map;
	private RobotVision vision;
    private List<RobotAi> robots;
    private List<RobotAi> idleRobots;
    private List<StorageRack> storageRacks;
	
	public RobotController() {
		// create map
		Map.Builder mapBuilder = new Map.Builder();
		mapBuilder.createGrid(RobotController.GRID_SIZE_X, RobotController.GRID_SIZE_Y);
		map = mapBuilder.build();
		
		// create vision
		RobotVision.Builder visionBuilder = new RobotVision.Builder();
		visionBuilder.createGrid(RobotController.GRID_SIZE_X, RobotController.GRID_SIZE_Y);
		visionBuilder.setVertexState(new Vector3D(7, 1, 0), RobotVision.NodeType.TRUCK_PICKUP_POINT);
		visionBuilder.setVertexState(new Vector3D(7, 3, 0), RobotVision.NodeType.TRUCK_DROPOFF_POINT);
		vision = visionBuilder.build();
		
		// spawn robots
		robots = new ArrayList<RobotAi>();
		idleRobots = new ArrayList<RobotAi>();
		for(int i = 0; i < RobotController.ROBOT_START_AMOUNT; i++) {
			RobotAi r = new RobotAi(this, RobotController.GRID_SPAWN_POINT);
			robots.add(r);
			idleRobots.add(r);
		}
		
		// init lists
		storageRacks = new ArrayList<StorageRack>();
	}
	
	public void run() {
		// update robots
		for(RobotAi robot : robots) {
			robot.update();
		}
		
		// check idle robots
		ListIterator<RobotAi> iter = idleRobots.listIterator();
		while(iter.hasNext()) {
			RobotAi robot = iter.next();
			RobotAi.Tasks task = robot.getTask();
			Path path;
			switch(task) {
				case NONE:
					robot.setTask(RobotAi.Tasks.LOAD);
					path = map.findShortestPath(robot.getTransform().getPosition(), vision.getTruckPickupPoint());
					robot.setPath(path);
					break;
				case LOAD:
					robot.setTask(RobotAi.Tasks.STORE);
					path = map.findShortestPath(robot.getTransform().getPosition(), vision.getStorageSpacePoint());
					robot.setPath(path);
					
					// create new rack
					StorageRack rack = new StorageRack();
					storageRacks.add(rack);
					
					robot.setStorageRack(rack);
					
					break;
				case DROP:
					break;
				case STORE:
					robot.setTask(RobotAi.Tasks.NONE);
					robot.setStorageRack(null);
					break;
				default:
					break;
			}
			iter.remove();
		}
	}
	
	public List<RobotAi> getRobots(){
		return this.robots;
	}
	
	public List<StorageRack> getStorageRack(){
		return this.storageRacks;
	}
	
	public void requestTask(RobotAi r) {
		idleRobots.add(r);
	}
}
