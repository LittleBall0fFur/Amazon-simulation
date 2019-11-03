package com.nhlstenden.amazonsimulation.robotai;

import java.util.List;

import com.nhlstenden.amazonsimulation.domain.Robot;
import com.nhlstenden.amazonsimulation.pathfinding.Map;

public class RobotController {

	private Map map;
	private List<Robot> robots;
	
	public RobotController() {
		// create map
		Map.Builder builder = new Map.Builder();
		builder.createGrid(8, 5);
		map = builder.build();
	}
	
}
