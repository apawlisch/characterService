package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Adventurer;
import com.revature.services.AdventurerService;

import reactor.core.publisher.Mono;

@RestController
public class AdventurerController {
	private AdventurerService advService;
	private final Adventurer noAdventurer = new Adventurer();
	
	@Autowired
	public void setAdvService(AdventurerService service) {
		this.advService = service;
	}
	
	// get adventurer
	@GetMapping("{name}")
	public Mono<ResponseEntity<Adventurer>> getAdventurer(@PathVariable("name") String name) {
		return advService.getAdventurer(name).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
		
	}
	
	// add health
	@PutMapping("{name}/hp/addition/{value}")
	public Mono<ResponseEntity<Adventurer>> addHealth(@PathVariable("name") String name, @PathVariable("value") Integer value) {
		return advService.addHealth(name, value).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// remove health
	@PutMapping("{name}/hp/subtraction/{value}")
	public Mono<ResponseEntity<Adventurer>> removeHealth(@PathVariable("name") String name, @PathVariable("value") Integer value) {
		return advService.removeHealth(name, value).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// restore health
	
	// set max health
	
	// reset max health
	
	// set traits
	
	// set speed
	
	// set initiative
	
	// set armor class
	
	// create adventurer
	@PostMapping
	public Mono<ResponseEntity<Adventurer>> createAdventurer(@RequestBody Adventurer newAdv) {
		if (newAdv.getCharacterName() == null) {
			return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(noAdventurer));
		}
		
		return advService.routeCreate(newAdv).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.CREATED).body(adv));
	}
	

}
