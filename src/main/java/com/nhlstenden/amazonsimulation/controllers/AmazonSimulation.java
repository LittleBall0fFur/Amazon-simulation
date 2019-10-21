package com.nhlstenden.amazonsimulation.controllers;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.nhlstenden.amazonsimulation.models.Warehouse;

@Controller
public class AmazonSimulation {
	
	private static final long FRAME_RATE = 60;
	private static final long UPDATE_INTERVAL_IN_MS = Math.round(1000.0 / FRAME_RATE);
	
	private SimpMessagingTemplate messagingService;
	
	private Warehouse warehouse;
	
	private Timer updateTimer;
	private TimerTask updateTask;
	
	@Autowired
	public AmazonSimulation(SimpMessagingTemplate messagingService) {
		this.messagingService = messagingService;
		
		this.warehouse = new Warehouse(/* PASS PARAMETERS LIKE 'AMOUNT OF ROBOTS' ETC */);
		
		/* ADD LISTENERS TO MODEL (WAREHOUSE) */
		//	EXAMPLE:
		//	this.warehouse.addPropertyChangeListener(Warehouse.ListenableProperty.ROBOT_POSITION, (event) -> {
		// 		sendRobotPositionToClient((Vector3D) event.getNewValue()); //Convert event to expected object and pass to handling function.
		// 	});
		//
		// 	Repeat for other properties.
		//
		//	Alternative: Make AmazonSimulation implement PropertyChangeListener and handle PropertyChangeEvents by a switch-case.
		
		/* START SIMULATION */
		this.updateTimer = new Timer();
		this.updateTask = new TimerTask() {
			
			@Override
			public void run() {
				update();
			}
			
		};
		
		this.updateTimer.schedule(updateTask, 0, UPDATE_INTERVAL_IN_MS);
	}
	
	private void update() {
		
	}
	
	//	EXAMPLE:
	//	private void sendRobotPositionToClient(Vector3D new_robotPosition) {
	//		this.messagingService.convertAndSend("topic/robot/position", new_robotPosition); //Send position to client.
	//	}
	
}
