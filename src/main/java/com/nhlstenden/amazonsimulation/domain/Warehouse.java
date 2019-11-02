package com.nhlstenden.amazonsimulation.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.nhlstenden.amazonsimulation.pathfinding.Map;

public class Warehouse {
	
	public enum ListenableProperty {
		STORAGE_RACK, TRUCK, ROBOT
	}

	private final PropertyChangeSupport eventService = new PropertyChangeSupport(this);
	
	private Map map;
	private Inventory inventory;
	
	private Truck truck;
	private List<Robot> robots;
	
	public Warehouse() {

	}

	public void update() {
		//	TODO: Implement
	}

	public void addPropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.addPropertyChangeListener(property.toString(), listener);
	}
	
	public void removePropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.removePropertyChangeListener(property.toString(), listener);
	}
	
}
