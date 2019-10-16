package com.nhlstenden.amazonsimulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.nhlstenden.amazonsimulation.models.Simulation;

@Controller
public class SimulationController {

	private SimpMessagingTemplate messagingService;
	
	private Simulation simulation;
	
	@Autowired
	public SimulationController(SimpMessagingTemplate messagingService) {
		this.messagingService = messagingService;
		
		this.simulation = new Simulation(/* PASS PARAMETERS LIKE 'AMOUNT OF ROBOTS' ETC */);
		
		/* ADD LISTENERS TO SIMULATION */
		//	EXAMPLE:
		//	this.simulation.addPropertyChangeListener(Simulation.ListenableProperty.ROBOT_POSITION, (event) -> {
		// 		sendRobotPositionToClient((Vector3D) event.getNewValue()); //Convert event to expected object and pass to handling function.
		// 	});
		//
		// 	Repeat for other properties.
		
		/* START SIMULATION ON ANOTHER THREAD */
		//	EXAMPLE: this.simulation.start();
	}
	
	//	EXAMPLE:
	//	private void sendRobotPositionToClient(Vector3D new_robotPosition) {
	//		this.messagingService.convertAndSend("topic/robot/position", new_robotPosition); //Send position to client.
	//	}
	
}
