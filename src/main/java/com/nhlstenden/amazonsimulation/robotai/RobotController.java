package com.nhlstenden.amazonsimulation.robotai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.domain.StorageRack;
import com.nhlstenden.amazonsimulation.domain.Warehouse;
import com.nhlstenden.amazonsimulation.pathfinding.Map;
import com.nhlstenden.amazonsimulation.pathfinding.Path;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotController {
	private static int ROBOT_START_AMOUNT = 2;
	private static Vector3D GRID_SPAWN_POINT = new Vector3D(4, 2, 0);
	public static int GRID_SIZE_X = 8;
	public static int GRID_SIZE_Y = 5;
	
	private Map map;
	private RobotVision vision;
    private List<RobotAi> robots;
    private List<RobotAi> idleRobots;
    private List<StorageRack> storageRacks;
    
    private Task task;
	
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
		task = new Task(Task.RobotControllerTasks.NONE);
	}
	
	public void run() {
		// update robots
		for(RobotAi robot : robots) {
			robot.update();
		}
		
		// check idle robots
		ListIterator<RobotAi> iter = idleRobots.listIterator();
		while(iter.hasNext()) {
			if(this.task.getTask() == Task.RobotControllerTasks.NONE) {
				break;
			}
			
			RobotAi robot = iter.next();
			RobotAi.RobotAiTasks task = robot.getTask();
			Path path;
			switch(task) {
				case NONE: // wait for load or unload task
					if(this.task.getTask() == Task.RobotControllerTasks.UNLOAD) { // check if task is unload then goto pickup point for truck
						robot.setTask(RobotAi.RobotAiTasks.LOAD);
						path = map.findShortestPath(robot.getTransform().getPosition(), vision.getTruckPickupPoint());
						robot.setPath(path);
						
						this.task.addOne();
					}
					
					if(this.task.getTask() == Task.RobotControllerTasks.LOAD) { // check if task is load then goto and find pickup point for storage
						robot.setTask(RobotAi.RobotAiTasks.PICKUP);
						Vector3D point = vision.getStorageSpacePickupPoint();
						if(point == null) {
							robot.setTask(RobotAi.RobotAiTasks.NONE);
						}
						path = map.findShortestPath(robot.getTransform().getPosition(), point);
						robot.setPath(path);
					}
					break;
				case LOAD: // take rack from truck
					robot.setTask(RobotAi.RobotAiTasks.STORE);
					Vector3D storageSpaceLoad = vision.getStorageSpaceDropPoint();
					if(storageSpaceLoad != null) {
						path = map.findShortestPath(robot.getTransform().getPosition(), storageSpaceLoad);
						robot.setPath(path);
						
						// create new rack
						StorageRack rack = new StorageRack();
						storageRacks.add(rack);
						
						robot.setStorageRack(rack);
					}else {
						// TODO clear storage (Tasks.PICKUP)
					}
					
					break;
				case STORE: // place rack on storage space
					robot.setTask(RobotAi.RobotAiTasks.NONE);
					vision.placeStorageRack(robot.getTransform().getPosition(), robot.getStorageRack());
					robot.setStorageRack(null);
					break;
				case PICKUP: // bring storage to truck
					robot.setTask(RobotAi.RobotAiTasks.PUTDOWN);
					StorageRack storageRack = vision.takeStorageRack(robot.getTransform().getPosition());
					if(storageRack != null) {
						path = map.findShortestPath(robot.getTransform().getPosition(), vision.getTruckDropoffPoint());
						robot.setPath(path);
						
						robot.setStorageRack(storageRack);
					}else {
						// TODO find new storage spot
					}
					break;
				case DROP:
					 // todo
					break;
				case PUTDOWN: // place rack in truck
					robot.setTask(RobotAi.RobotAiTasks.NONE);
					robot.setStorageRack(null);
					break;
				default:
					break;
			}
			iter.remove();
		}
		
		// check if task complete
		task.complete();
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
	
	public void setTask(Task task) {
		this.task = task;
	}
}
