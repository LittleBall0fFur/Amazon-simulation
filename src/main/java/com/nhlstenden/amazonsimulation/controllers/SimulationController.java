package com.nhlstenden.amazonsimulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SimulationController {

	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	public SimulationController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
}
