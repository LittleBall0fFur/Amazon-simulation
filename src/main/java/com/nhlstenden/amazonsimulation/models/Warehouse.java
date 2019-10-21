package com.nhlstenden.amazonsimulation.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Warehouse {
	
	public enum ListenableProperty {
		//	EXAMPLE: ROBOT_POSITION, ROBOT_ISLOADED, etc.
	}

	private final PropertyChangeSupport eventService;
	
	private Truck truck;
	private ArrayList<Robot> robots;
	
	public Warehouse() {
		this.eventService = new PropertyChangeSupport(this);
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
