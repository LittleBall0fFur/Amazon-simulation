package com.nhlstenden.amazonsimulation.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AmazonSimulation {
	
	public enum ListenableProperty {
		//	EXAMPLE: ROBOT_POSITION, ROBOT_ISLOADED, etc.
	}
	
	private final PropertyChangeSupport eventService;
	
	public AmazonSimulation() {
		this.eventService = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.addPropertyChangeListener(property.toString(), listener);
	}
	
	public void removePropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		this.eventService.removePropertyChangeListener(property.toString(), listener);
	}
	
}
