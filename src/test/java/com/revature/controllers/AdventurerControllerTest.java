package com.revature.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.beans.Adventurer;
import com.revature.services.AdventurerService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AdventurerControllerTest {
	
	@InjectMocks
	private AdventurerController controller;
	
	@Mock
	private AdventurerService service;
	
	private static Adventurer adv;
	private static Adventurer adv2;
	
	@BeforeAll
	static void setUp() {
		adv = new Adventurer();
		adv2 = new Adventurer();
	}
	
	@BeforeEach
	void setUpTests() {
		MockitoAnnotations.openMocks(this);
		
		adv.setCharacterName("name");
		adv.setCurrentHitPoints(30);
		adv.setMaxHitPoints(40);
		adv.setStrength(null);
		adv.setConstitution(null);
		adv.setIntelligence(null);
		adv.setWisdom(null);
		adv.setCharisma(null);
		adv.setDexterity(null);
		adv.setSpeed(null);
		adv.setArmorClass(null);
		adv.setInitiative(null);
		
		adv2.setCharacterName("name");
		adv2.setCurrentHitPoints(30);
		adv2.setMaxHitPoints(40);
		adv2.setStrength(null);
		adv2.setConstitution(null);
		adv2.setIntelligence(null);
		adv2.setWisdom(null);
		adv2.setCharisma(null);
		adv2.setDexterity(null);
		adv2.setSpeed(null);
		adv2.setArmorClass(null);
		adv2.setInitiative(null);
	}
	
	// get adventurer
	@Test
	void testGetAdventurer() {
		Mockito.when(service.getAdventurer("name")).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.getAdventurer("name"))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv))
		.verifyComplete();
		
	}
	
	// add health
	@Test
	void testAddHealth() {
		adv2.setCurrentHitPoints(32);
		
		Mockito.when(service.addHealth("name", 2)).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(controller.addHealth("name", 2))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv2))
		.verifyComplete();
	}
	
	// remove health
	@Test
	void testRemovehealth() {
		adv2.setCurrentHitPoints(28);
		
		Mockito.when(service.addHealth("name", 2)).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(controller.addHealth("name", 2))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv2))
		.verifyComplete();
	}
	
	// restore health
	@Test
	void testRestoreHealth() {
		adv2.setCurrentHitPoints(adv.getMaxHitPoints());
		adv2.setMaxHitPoints(adv.getMaxHitPoints());
		
		Mockito.when(service.restoreHealth(Mockito.any())).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(controller.restoreHealth("name"))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv2))
		.verifyComplete();
	}
	
	// set max health
	@Test
	void testSetMaxHealth() {
		adv2.setMaxHitPoints(50);
		
		Mockito.when(service.setMaxHealth("name", 50)).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(controller.setMaxHealth("name", 50))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv2))
		.verifyComplete();
	}
	
	// reset max health
	@Test
	void testResetMaxHealth() {
		adv2.setConstitution(5);
		adv2.setMaxHitPoints(10);
		
		Mockito.when(service.resetMaxHealth("name")).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(controller.resetMaxHealth("name"))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv2))
		.verifyComplete();
		
	}
	
	// set traits
	@Test
	void testSetTraits() {
		adv.setStrength(1);
		adv.setConstitution(1);
		adv.setIntelligence(1);
		adv.setWisdom(1);
		adv.setCharisma(1);
		adv.setDexterity(1);
		
		Mockito.when(service.setTraits("name", adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.setTraits("name", adv))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv))
		.verifyComplete();
	}
	
	// set speed
	@Test
	void testSetSpeed() {
		adv.setSpeed(10);
		
		Mockito.when(service.setSpeed("name", 10)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.setSpeed("name", 10))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv))
		.verifyComplete();
	}
	
	// reset speed
	@Test
	void testResetSpeed() {
		adv.setSpeed(30);
		adv.setDexterity(5);
		
		Mockito.when(service.resetSpeed("name")).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.resetSpeed("name"))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv))
		.verifyComplete();
	}
	
	// set initiative
	@Test
	void testSetInitiative() {
		adv.setInitiative(7);
		
		Mockito.when(service.setInitiative("name", 7)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.setInitiative("name", 7))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv))
		.verifyComplete();
	}
	
	// set armor class
	@Test
	void testSetArmorClass() {
		adv.setArmorClass(7);
		
		Mockito.when(service.setArmorClass("name", 7)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.setArmorClass("name", 7))
		.expectNext(ResponseEntity.status(HttpStatus.OK).body(adv))
		.verifyComplete();
	}
	
	// create adventurer (name)
	@Disabled
	@Test
	void createAdventurerName() {
		Mockito.when(service.routeCreate(Mockito.any())).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.createAdventurer(adv))
		.expectNext(ResponseEntity.status(HttpStatus.CREATED).body(adv))
		.verifyComplete();
	}
	
	// create adventurer (name + traits)
	@Test
	void createAdventurerNameTraits() {
		adv.setStrength(1);
		adv.setConstitution(1);
		adv.setIntelligence(1);
		adv.setWisdom(1);
		adv.setCharisma(1);
		adv.setDexterity(1);
		
		Mockito.when(service.routeCreate(Mockito.any())).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.createAdventurer(adv))
		.expectNext(ResponseEntity.status(HttpStatus.CREATED).body(adv))
		.verifyComplete();
	}
	
	// create adventurer (name + armor)
	@Test
	void createAdventurerNameArmor() {
		adv.setArmorClass(1);
		
		Mockito.when(service.routeCreate(Mockito.any())).thenReturn(Mono.just(adv));
		
		StepVerifier.create(controller.createAdventurer(adv))
		.expectNext(ResponseEntity.status(HttpStatus.CREATED).body(adv))
		.verifyComplete();
	}
	
	// create adventurer (name + traits + armor)
	@Test
	void createAdventurerNameTraitsArmor() {
		adv.setStrength(1);
		adv.setConstitution(1);
		adv.setIntelligence(1);
		adv.setWisdom(1);
		adv.setCharisma(1);
		adv.setDexterity(1);
		adv.setArmorClass(1);
		
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setDexterity(1);
		adv2.setArmorClass(1);
		adv2.setInitiative(null);
		adv2.setSpeed(26);
		adv2.setCurrentHitPoints(6);
		adv2.setMaxHitPoints(6);
		
		Mockito.when(service.routeCreate(Mockito.any())).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(controller.createAdventurer(adv))
		.expectNext(ResponseEntity.status(HttpStatus.CREATED).body(adv2))
		.verifyComplete();
	}
	

}
