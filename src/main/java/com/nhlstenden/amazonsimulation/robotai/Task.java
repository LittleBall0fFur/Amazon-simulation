package com.nhlstenden.amazonsimulation.robotai;

import com.nhlstenden.amazonsimulation.domain.Warehouse;


public class Task{
	public enum RobotControllerTasks { NONE, UNLOAD, LOAD };
	
	private RobotControllerTasks task;
	private int completeAmount;
	private int currentAmount;
	private Warehouse warehouse;
	
	public Task(RobotControllerTasks task, int completeAmount, Warehouse warehouse) {
		this.task = task;
		this.completeAmount = completeAmount;
		this.warehouse = warehouse;
	}
	
	public Task(RobotControllerTasks task) {
		this.task = task;
		this.completeAmount = 0;
	}
	
	public void addOne() {
		this.currentAmount++;
	}
	
	public void complete() {
		if(this.isCompleted()) {
			this.warehouse.robotsCompleted();
		}
	}
	
	public boolean isCompleted() {
		return currentAmount >= completeAmount && task != RobotControllerTasks.NONE;
	}
	
	public RobotControllerTasks getTask() {
		return task;
	}
}
