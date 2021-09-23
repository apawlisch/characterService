package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.AdventurerService;

@RestController
public class AdventurerController {
	private AdventurerService advService;
	
	@Autowired
	public void setAdvService(AdventurerService service) {
		this.advService = service;
	}
	
	// get character
	
	// add health
	
	// remove health
	
	// restore health
	
	// set max health
	
	// reset max health
	
	// set traits
	
	// set speed
	
	// set initiative
	
	// set armor class
	
	// create character
	

}
