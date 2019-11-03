package com.nhlstenden.amazonsimulation.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.nhlstenden.amazonsimulation.pathfinding.Map;
import com.nhlstenden.amazonsimulation.physics.Vector3D;
import com.nhlstenden.amazonsimulation.robotai.RobotAi;
import com.nhlstenden.amazonsimulation.robotai.RobotController;

public class Warehouse {
	
	public enum ListenableProperty {
		STORAGE_RACK, TRUCK, ROBOT
	}

	private final PropertyChangeSupport eventService = new PropertyChangeSupport(this);
	
	private RobotController robotController;
	
	private Inventory inventory;
	
	private Truck truck;
	
	public Warehouse() {
		this.robotController = new RobotController();
	}

	public void update() {
		this.robotController.run();
		
		// update robots with clients
		List<RobotAi> robotList = this.robotController.getRobots();
		for(RobotAi r : robotList) {
			eventService.firePropertyChange(ListenableProperty.ROBOT.toString(), null, r);
		}
		
		// update storageracks with clients
		List<StorageRack> storageRackList = this.robotController.getStorageRack();
		for(StorageRack s : storageRackList) {
			eventService.firePropertyChange(ListenableProperty.STORAGE_RACK.toString(), null, s);
		}
	}

	public void addPropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.addPropertyChangeListener(property.toString(), listener);
	}
	
	public void removePropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.removePropertyChangeListener(property.toString(), listener);
	}
	
}
