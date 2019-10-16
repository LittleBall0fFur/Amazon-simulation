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
	}
	
}
