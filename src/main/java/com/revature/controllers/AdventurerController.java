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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AdventurerController {
	private AdventurerService advService;
	private final Adventurer noAdventurer = new Adventurer();
	
	@Autowired
	public void setAdvService(AdventurerService service) {
		this.advService = service;
	}
	
	// basic get response for the service
	@GetMapping
	public ResponseEntity<String> getBasic() {
		return ResponseEntity.status(HttpStatus.OK).body("Hello");
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
	@PutMapping("{name}/hp")
	public Mono<ResponseEntity<Adventurer>> restoreHealth(@PathVariable("name") String name) {
		return advService.restoreHealth(name).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// set max health
	@PutMapping("{name}/hp/maximum/{value}")
	public Mono<ResponseEntity<Adventurer>> setMaxHealth(@PathVariable("name") String name, @PathVariable("value") Integer value) {
		return advService.setMaxHealth(name, value).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// reset max health
	@PutMapping("{name}/hp/maximum")
	public Mono<ResponseEntity<Adventurer>> resetMaxHealth(@PathVariable("name") String name) {
		return advService.resetMaxHealth(name).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// set traits
	@PutMapping("{name}/traits")
	public Mono<ResponseEntity<Adventurer>> setTraits(@PathVariable("name") String name, @RequestBody Adventurer traits) {
		// get traits as adventurer object in request body
		return advService.setTraits("name", traits).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// set speed
	@PutMapping("{name}/speed/{value}")
	public Mono<ResponseEntity<Adventurer>> setSpeed(@PathVariable("name") String name, @PathVariable("value") Integer value) {
		return advService.setSpeed("name", value).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// reset speed
	@PutMapping("{name}/speed")
	public Mono<ResponseEntity<Adventurer>> resetSpeed(@PathVariable("name") String name) {
		return advService.resetSpeed("name").defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// set initiative
	@PutMapping("{name}/initiative/{value}")
	public Mono<ResponseEntity<Adventurer>> setInitiative(@PathVariable("name") String name, @PathVariable("value") Integer value) {
		return advService.setInitiative("name", value).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
	// set armor class
	@PutMapping("{name}/armorclass/{value}")
	public Mono<ResponseEntity<Adventurer>> setArmorClass(@PathVariable("name") String name, @PathVariable("value") Integer value) {
		return advService.setArmorClass("name", value).defaultIfEmpty(noAdventurer)
				.map(adv -> ResponseEntity.status(HttpStatus.OK).body(adv));
	}
	
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
