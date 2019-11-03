package com.nhlstenden.amazonsimulation.robotai;

import java.util.ArrayList;
import java.util.List;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.pathfinding.Map;
import com.nhlstenden.amazonsimulation.physics.Vector3D;

public class RobotController {

	private static Vector3D GRID_SPAWN_POINT = new Vector3D(4, 2, 0);
	private static int GRID_SIZE_X = 8;
	private static int GRID_SIZE_Y = 5;
	
	private Map map;
	private RobotVision vision;
    public List<RobotAi> robots;
	
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
		r.setTarget(new Vector3D(3,2,0));
		robots.add(r);
	}
	
	public void run() {
		for(RobotAi robot : robots) {
			robot.update();
			System.out.println("robot position: " + robot.getTransform().getPosition().toString());
		}
	}
	
	public void help(Robot r) {
		System.out.println("robot at target!");
	}
	
}
