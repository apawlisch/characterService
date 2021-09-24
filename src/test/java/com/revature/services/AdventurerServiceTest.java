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
	static void setUp() {
		adv = new Adventurer();
		adv.setCharacterName("testAdv");
		/*
		adv.setStrength(1);
		adv.setConstitution(1);
		adv.setIntelligence(1);
		adv.setWisdom(1);
		adv.setCharisma(1);
		adv.setDexterity(1);
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
		adv.setDexterity(1);
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
	
	
	// get adventurer
	@Test
	void testGetAdventurer() {
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.getAdventurer("testAdv"))
			.expectNext(adv)
			.verifyComplete();
		
	}
	
	// add health
	@Test
	void testAddHealth() {
		adv2.setCurrentHitPoints(32);
		adv2.setCharacterName("testAdv");
		adv2.setMaxHitPoints(adv.getMaxHitPoints()); 
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.addHealth("testAdv", 2))
				.expectNextMatches(newAdv -> {
					return adv2.getCharacterName().equals(newAdv.getCharacterName())
							&& adv2.getCurrentHitPoints().equals(newAdv.getCurrentHitPoints());
				})
				.verifyComplete();
		
	}
	
	// subtract health
	@Test
	void testRemoveHealth() {
		adv2.setCurrentHitPoints(28);
		adv2.setCharacterName("testAdv");
		adv2.setMaxHitPoints(adv.getMaxHitPoints()); 
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.removeHealth("testAdv", 2))
				.expectNextMatches(newAdv -> {
					return adv2.getCharacterName().equals(newAdv.getCharacterName())
							&& adv2.getCurrentHitPoints().equals(newAdv.getCurrentHitPoints());
				})
				.verifyComplete();
		
		
	}
	
	// restore health to max
	@Test
	void testRestoreHealth() {
		adv2.setCurrentHitPoints(40);
		adv2.setMaxHitPoints(40);
		adv2.setCharacterName("testAdv");
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.restoreHealth("testAdv"))
				.expectNextMatches(newAdv -> {
					return adv2.getCharacterName().equals(newAdv.getCharacterName())
							&& adv2.getCurrentHitPoints().equals(newAdv.getCurrentHitPoints());
				})
				.verifyComplete();
	}
	
	
	// set max health
	@Test
	void testSetMaxHealth() {
		adv2.setMaxHitPoints(50);
		adv2.setCharacterName("testAdv");
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.setMaxHealth("testAdv", 50))
			.expectNextMatches(newAdv -> {
				return adv2.getCharacterName().equals(newAdv.getCharacterName())
						&& adv2.getMaxHitPoints().equals(newAdv.getMaxHitPoints());
			})
			.verifyComplete();
	}
	
	// reset max health
	@Test
	void testResetMaxHealth() {
		adv.setConstitution(5);
		adv2.setCharacterName("testAdv");
		adv2.setMaxHitPoints(10);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.resetMaxHealth("testAdv"))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getMaxHitPoints().equals(newAdv.getMaxHitPoints());
		})
		.verifyComplete();
		
	}
	
	// set traits
	@Test
	void testSetTraits() {
		Adventurer setTraits = new Adventurer();
		setTraits.setStrength(1);
		setTraits.setConstitution(1);
		setTraits.setIntelligence(1);
		setTraits.setWisdom(1);
		setTraits.setCharisma(1);
		setTraits.setDexterity(1);
		
		adv2.setCharacterName("testAdv");
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setDexterity(1);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.setTraits("testAdv", setTraits))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getStrength().equals(newAdv.getStrength())
					&& adv2.getConstitution().equals(newAdv.getConstitution())
					&& adv2.getIntelligence().equals(newAdv.getIntelligence())
					&& adv2.getWisdom().equals(newAdv.getWisdom())
					&& adv2.getCharisma().equals(newAdv.getCharisma())
					&& adv2.getDexterity().equals(newAdv.getDexterity());
		})
		.verifyComplete();
		
	}
	
	// set speed
	@Test
	void testSetSpeed() {
		
	}
	
	// reset speed
	@Test
	void testResetSpeed() {
		
	}
	
	// set initiative
	
	// set armor class
	
	// route create
	
	// create with name
	
	// create with name + traits
	
	// create with name + armor class
	
	// create with name + traits + armor
	
	

}
