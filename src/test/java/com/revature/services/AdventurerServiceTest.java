package com.revature.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.beans.Adventurer;
import com.revature.data.AdventurerDao;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class AdventurerServiceTest {
	
	@InjectMocks
	private AdventurerServiceImpl service;
	
	@Mock
	private AdventurerDao advDao;
	
	private static Adventurer adv;
	private static Adventurer adv2;
	
	@BeforeAll
	static void setup() {
		adv = new Adventurer();
		adv.setCharacterName("testAdv");
		/*
		adv.setStrength(1);
		adv.setConstitution(1);
		adv.setIntelligence(1);
		adv.setWisdom(1);
		adv.setCharisma(1);
		adv.setSpeed(1);
		adv.setArmorClass(1);
		adv.setInitiative(1);
		*/
		
		adv2 = new Adventurer();
		//adv2.setCharacterName("testAdv2");
		/*
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setSpeed(1);
		adv2.setArmorClass(1);
		adv2.setInitiative(1);
		*/
		
	}
	
	@BeforeEach
	void setUpTests() {
		// initializes the mockito annotations above
		MockitoAnnotations.openMocks(this);
		
		adv.setCurrentHitPoints(30);
		adv.setMaxHitPoints(40);
		
	}
	
	// add health
	@Test
	void testAddHealth() {
		adv2.setCurrentHitPoints(32);
		adv2.setCharacterName("testAdv");
		adv2.setMaxHitPoints(40);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.addHealth("testAdv", 2))
				.expectNext(adv2)
				.verifyComplete();
		
		
	}
	
	// subtract health
	@Disabled
	@Test
	void testRemoveHealth() {
		adv2.setCurrentHitPoints(28);
		adv2.setCharacterName("testAdv");
		adv2.setMaxHitPoints(40);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.removeHealth("testAdv", 2))
				.expectNext(adv2)
				.verifyComplete();
		
		
	}
	
	// restore health to max
	@Disabled
	@Test
	void testRestoreHealth() {
		adv2.setCurrentHitPoints(40);
		adv2.setMaxHitPoints(40);
		adv2.setCharacterName("testAdv");
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.restoreHealth("testAdv"))
				.expectNext(adv2)
				.verifyComplete();
	}
	
	
	// set max health

}
