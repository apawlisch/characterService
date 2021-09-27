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
		
	}
	
	@BeforeEach
	void setUpTests() {
		// initializes the mockito annotations above
		MockitoAnnotations.openMocks(this);
		
		// reset for tests
		adv.setCharacterName("testAdv");
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
		adv2.setCharacterName("testAdv");
		adv2.setSpeed(30);
		
		adv.setSpeed(10);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.setSpeed("testAdv", 30))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getSpeed().equals(newAdv.getSpeed());
		})
		.verifyComplete();
	}
	
	// reset speed
	@Test
	void testResetSpeed() {
		adv2.setCharacterName("testAdv");
		adv2.setDexterity(5);
		adv2.setSpeed(30);
		
		adv.setDexterity(5);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.resetSpeed("testAdv"))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getSpeed().equals(newAdv.getSpeed());
		})
		.verifyComplete();
	}
	
	// set initiative
	@Test
	void testSetInitiative() {
		adv2.setCharacterName("testAdv");
		adv2.setInitiative(7);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.setInitiative("testAdv", 7))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getInitiative().equals(newAdv.getInitiative());
		})
		.verifyComplete();
		
	}
	
	// set armor class
	@Test
	void testSetArmorClass() {
		adv2.setCharacterName("testAdv");
		adv2.setArmorClass(10);
		
		Mockito.when(advDao.findByCharacterName("testAdv")).thenReturn(Mono.just(adv));
		Mockito.when(advDao.save(adv)).thenReturn(Mono.just(adv));
		
		StepVerifier.create(service.setArmorClass("testAdv", 10))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getArmorClass().equals(newAdv.getArmorClass());
		})
		.verifyComplete();
	}
	
	// create with name
	@Test
	void testCreateWithName() {
		adv2.setCharacterName("name");
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(createAdv));
		
		StepVerifier.create(service.createWithName(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& newAdv.getArmorClass() == null
					&& newAdv.getStrength() == null
					&& newAdv.getDexterity() == null
					&& newAdv.getConstitution() == null
					&& newAdv.getIntelligence() == null
					&& newAdv.getWisdom() == null
					&& newAdv.getCharisma() == null;
		})
		.verifyComplete();
	}
	
	// create with name + traits
	@Test
	void testCreateWithNameTraits() {
		adv2.setCharacterName("name");
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setDexterity(1);
		adv2.setMaxHitPoints(6);
		adv2.setCurrentHitPoints(6);
		adv2.setSpeed(26);
		adv2.setArmorClass(null);
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		createAdv.setStrength(1);
		createAdv.setConstitution(1);
		createAdv.setIntelligence(1);
		createAdv.setWisdom(1);
		createAdv.setCharisma(1);
		createAdv.setDexterity(1);

		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(service.createWithNameTraits(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& newAdv.getArmorClass() == null
					&& adv2.getStrength().equals(newAdv.getStrength())
					&& adv2.getDexterity().equals(newAdv.getDexterity())
					&& adv2.getConstitution().equals(newAdv.getConstitution())
					&& adv2.getIntelligence().equals(newAdv.getIntelligence())
					&& adv2.getWisdom().equals(newAdv.getWisdom())
					&& adv2.getCharisma().equals(newAdv.getCharisma())
					&& adv2.getMaxHitPoints().equals(newAdv.getMaxHitPoints())
					&& adv2.getCurrentHitPoints().equals(newAdv.getCurrentHitPoints())
					&& adv2.getSpeed().equals(newAdv.getSpeed());
		})
		.verifyComplete();
	}
	
	// create with name + armor class
	@Test
	void testCreateWithNameArmor() {
		adv2.setCharacterName("name");
		adv2.setArmorClass(10);
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		createAdv.setArmorClass(10);
		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(service.createWithNameArmor(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getArmorClass().equals(newAdv.getArmorClass())
					&& newAdv.getStrength() == null
					&& newAdv.getDexterity() == null
					&& newAdv.getConstitution() == null
					&& newAdv.getIntelligence() == null
					&& newAdv.getWisdom() == null
					&& newAdv.getCharisma() == null;
		})
		.verifyComplete();
	}
	
	// create with name + traits + armor
	@Test
	void testCreateWithNameTraitsArmor() {
		adv2.setCharacterName("name");
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setDexterity(1);
		adv2.setArmorClass(10);
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		createAdv.setStrength(1);
		createAdv.setConstitution(1);
		createAdv.setIntelligence(1);
		createAdv.setWisdom(1);
		createAdv.setCharisma(1);
		createAdv.setDexterity(1);
		createAdv.setArmorClass(10);
		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(createAdv));
		
		StepVerifier.create(service.createWithNameTraitsArmor(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getArmorClass().equals(newAdv.getArmorClass())
					&& adv2.getStrength().equals(newAdv.getStrength())
					&& adv2.getDexterity().equals(newAdv.getDexterity())
					&& adv2.getConstitution().equals(newAdv.getConstitution())
					&& adv2.getIntelligence().equals(newAdv.getIntelligence())
					&& adv2.getWisdom().equals(newAdv.getWisdom())
					&& adv2.getCharisma().equals(newAdv.getCharisma())
					&& newAdv.getInitiative() == null;
		})
		.verifyComplete();
	}
	
	
	// routeCreate for name
	// tests that it routes to create the correct adventurer
	@Test
	void testRouteCreateName() {
		adv2.setCharacterName("name");
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(createAdv));
		
		StepVerifier.create(service.routeCreate(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& newAdv.getArmorClass() == null
					&& newAdv.getStrength() == null
					&& newAdv.getDexterity() == null
					&& newAdv.getConstitution() == null
					&& newAdv.getIntelligence() == null
					&& newAdv.getWisdom() == null
					&& newAdv.getCharisma() == null;
		})
		.verifyComplete();
		
	}
	
	// routeCreate for name + traits
	// tests that it routes to create the correct adventurer
	@Test
	void testRouteCreateNameTraits() {
		adv2.setCharacterName("name");
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setDexterity(1);
		adv2.setMaxHitPoints(6);
		adv2.setCurrentHitPoints(6);
		adv2.setSpeed(26);
		adv2.setArmorClass(null);
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		createAdv.setStrength(1);
		createAdv.setConstitution(1);
		createAdv.setIntelligence(1);
		createAdv.setWisdom(1);
		createAdv.setCharisma(1);
		createAdv.setDexterity(1);

		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(service.routeCreate(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& newAdv.getArmorClass() == null
					&& adv2.getStrength().equals(newAdv.getStrength())
					&& adv2.getDexterity().equals(newAdv.getDexterity())
					&& adv2.getConstitution().equals(newAdv.getConstitution())
					&& adv2.getIntelligence().equals(newAdv.getIntelligence())
					&& adv2.getWisdom().equals(newAdv.getWisdom())
					&& adv2.getCharisma().equals(newAdv.getCharisma())
					&& adv2.getMaxHitPoints().equals(newAdv.getMaxHitPoints())
					&& adv2.getCurrentHitPoints().equals(newAdv.getCurrentHitPoints())
					&& adv2.getSpeed().equals(newAdv.getSpeed());
		})
		.verifyComplete();
	}
	
	// routeCreate for name + armor
	// tests that it routes to create the correct adventurer
	@Test
	void testRouteCreateNameArmor() {
		adv2.setCharacterName("name");
		adv2.setArmorClass(10);
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		createAdv.setArmorClass(10);
		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(adv2));
		
		StepVerifier.create(service.routeCreate(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getArmorClass().equals(newAdv.getArmorClass())
					&& newAdv.getStrength() == null
					&& newAdv.getDexterity() == null
					&& newAdv.getConstitution() == null
					&& newAdv.getIntelligence() == null
					&& newAdv.getWisdom() == null
					&& newAdv.getCharisma() == null;
		})
		.verifyComplete();
	}
	
	// routeCreate for name + traits + armor
	// tests that it routes to create the correct adventurer
	@Test
	void testRouteCreateNameTraitsArmor() {
		adv2.setCharacterName("name");
		adv2.setStrength(1);
		adv2.setConstitution(1);
		adv2.setIntelligence(1);
		adv2.setWisdom(1);
		adv2.setCharisma(1);
		adv2.setDexterity(1);
		adv2.setArmorClass(10);
		
		Adventurer createAdv = new Adventurer();
		createAdv.setCharacterName("name");
		createAdv.setStrength(1);
		createAdv.setConstitution(1);
		createAdv.setIntelligence(1);
		createAdv.setWisdom(1);
		createAdv.setCharisma(1);
		createAdv.setDexterity(1);
		createAdv.setArmorClass(10);
		
		Mockito.when(advDao.save(Mockito.any())).thenReturn(Mono.just(createAdv));
		
		StepVerifier.create(service.routeCreate(createAdv))
		.expectNextMatches(newAdv -> {
			return adv2.getCharacterName().equals(newAdv.getCharacterName())
					&& adv2.getArmorClass().equals(newAdv.getArmorClass())
					&& adv2.getStrength().equals(newAdv.getStrength())
					&& adv2.getDexterity().equals(newAdv.getDexterity())
					&& adv2.getConstitution().equals(newAdv.getConstitution())
					&& adv2.getIntelligence().equals(newAdv.getIntelligence())
					&& adv2.getWisdom().equals(newAdv.getWisdom())
					&& adv2.getCharisma().equals(newAdv.getCharisma())
					&& newAdv.getInitiative() == null;
		})
		.verifyComplete();
	}
	
	

}
