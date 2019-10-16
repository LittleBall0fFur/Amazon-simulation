package com.nhlstenden.amazonsimulation.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Simulation {
	
	public enum ListenableProperty {
		//	EXAMPLE: ROBOT_POSITION, ROBOT_ISLOADED, etc.
	}
	
	private final PropertyChangeSupport eventService;
	
	public Simulation() {
		this.eventService = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.addPropertyChangeListener(property.toString(), listener);
	}
	
	public void removeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.removePropertyChangeListener(property.toString(), listener);
	}
	
}
