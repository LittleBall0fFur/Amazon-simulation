package com.nhlstenden.amazonsimulation;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.nhlstenden.amazonsimulation.domain.Warehouse;
import com.nhlstenden.amazonsimulation.domain.Warehouse.ListenableProperty;

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
		
		/* REGISTER LISTENERS */
		this.warehouse.addPropertyChangeListener(ListenableProperty.STORAGE_RACK, (event) -> {
			publishStorageRackUpdate(event.getNewValue());
		});
		
		this.warehouse.addPropertyChangeListener(ListenableProperty.TRUCK, (event) -> {
			publishTruckUpdate(event.getNewValue());
		});
		
		this.warehouse.addPropertyChangeListener(ListenableProperty.ROBOT, (event) -> {
			publishRobotUpdate(event.getNewValue());
		});
		
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
	
	/**
	 * Update the simulation. Currently simply forwards the update to the Warehouse Object.
	 */
	private void update() {
		this.warehouse.update();
	}
	
	/**
	 * Request the current status of the whole simulation. This method is supposed to be called once
	 * by the View upon connecting. The View should then subscribe to the update channels.
	 * @return The current status of the warehouse.
	 */
	@MessageMapping("/warehouse")
	@SendToUser("/queue/warehouse")
	public Object requestWarehouseStatus() {
		return new Object(); /* Return full current state of the simulation */
	}
	
	/**
	 * Update every View on the current status of a storage rack.
	 * @param racks The new status of a storage rack.
	 */
	private void publishStorageRackUpdate(/*State of Rack*/Object storageRack) {
		this.messagingService.convertAndSend("/topic/storage-rack", storageRack);
	}
	
	/**
	 * Update every View on the current status of the truck.
	 * @param truck The new status of the truck.
	 */
	private void publishTruckUpdate(/*State of Truck*/Object truck) {
		this.messagingService.convertAndSend("/topic/truck", truck);
	}
	
	/**
	 * Update every View on the current status of a robot.
	 * @param robot The new status of a robot.
	 */
	private void publishRobotUpdate(/*State of Robot*/Object robot) {
		this.messagingService.convertAndSend("/topic/robot", robot);
	}
	
	/**
	 * Handles exceptions thrown from STOMP message-handling methods by printing the stack trace
	 * and sending the attached message to affected Views.
	 * @param exception The exception that was thrown.
	 * @return The message to be sent to affected Views. Sending this message is handled by Spring Boot.
	 */
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	private String handleMessageException(Throwable exception) {
		exception.printStackTrace();
		return exception.getMessage();
	}
	
}
