package com.nhlstenden.amazonsimulation.robotai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.pathfinding.Map;
import com.nhlstenden.amazonsimulation.pathfinding.Path;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotController {

	private static Vector3D GRID_SPAWN_POINT = new Vector3D(4, 2, 0);
	private static int GRID_SIZE_X = 8;
	private static int GRID_SIZE_Y = 5;
	
	private Map map;
	private RobotVision vision;
    private List<RobotAi> robots;
	
	public RobotController() {
		// create map
		Map.Builder mapBuilder = new Map.Builder();
		mapBuilder.createGrid(RobotController.GRID_SIZE_X, RobotController.GRID_SIZE_Y);
		map = mapBuilder.build();
		
		// create vision
		RobotVision.Builder visionBuilder = new RobotVision.Builder();
		visionBuilder.createGrid(RobotController.GRID_SIZE_X, RobotController.GRID_SIZE_Y);
		visionBuilder.setVertexState(new Vector3D(2, 4, 0), RobotVision.NodeType.TRUCK_PICKUP_POINT);
		visionBuilder.setVertexState(new Vector3D(6, 4, 0), RobotVision.NodeType.TRUCK_DROPOFF_POINT);
		vision = visionBuilder.build();
		
		// add temp robot
		robots = new ArrayList<RobotAi>();
		RobotAi r = new RobotAi(this, RobotController.GRID_SPAWN_POINT);
		robots.add(r);
		this.help(r);
		
		RobotAi b = new RobotAi(this, RobotController.GRID_SPAWN_POINT);
		robots.add(b);
		this.help(b);
	}
	
	public void run() {
		for(RobotAi robot : robots) {
			robot.update();
			//System.out.println("robot position: " + robot.getTransform().getPosition().toString());
		}
	}
	
	public List<RobotAi> getRobots(){
		return this.robots;
	}
	
	public void help(RobotAi r) {
		System.out.println("robot at target!");
		Vector3D endPoint = new Vector3D(new Random().nextInt(8), new Random().nextInt(5), 0);
		Path p = map.findShortestPath(r.getTransform().getPosition(), endPoint);
		r.setPath(p);
	}
	
}
