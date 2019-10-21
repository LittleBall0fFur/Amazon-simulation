package com.nhlstenden.amazonsimulation.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class AmazonSimulation {
	
	public enum ListenableProperty {
		//	EXAMPLE: ROBOT_POSITION, ROBOT_ISLOADED, etc.
	}
	
	private static final long FRAME_RATE = 60;
	private static final long UPDATE_INTERVAL_IN_MS = Math.round(1000.0 / FRAME_RATE);
	
	private final Timer updateTimer;
	private final TimerTask updateTask;
	private boolean isRunning;
	
	private final PropertyChangeSupport eventService;
	
	public AmazonSimulation() {
		this.updateTimer = new Timer();
		this.updateTask = new TimerTask() {
			
			@Override
			public void run() {
				update();
			}
			
		};
		
		this.eventService = new PropertyChangeSupport(this);
	}
	
	public synchronized void start() {
		if (this.isRunning != true) {
			this.updateTimer.schedule(updateTask, 0, UPDATE_INTERVAL_IN_MS);
			this.isRunning = true;
		} else {
			throw new IllegalStateException("");
		}
	}

	private void update() {
		//	TODO: Implement
	}
	
	public synchronized void stop() {
		if (this.isRunning == true) {
			this.updateTimer.cancel();
			this.isRunning = false;
		} else {
			throw new IllegalStateException("");
		}
	}
	
	public void addPropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		synchronized (this.eventService) {
			this.eventService.addPropertyChangeListener(property.toString(), listener);
		}
	}
	
	public void removePropertyChangeListener(ListenableProperty property, PropertyChangeListener listener) {
		synchronized (this.eventService) {
			this.eventService.removePropertyChangeListener(property.toString(), listener);
		}
	}
	
}
