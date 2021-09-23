package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Adventurer;
import com.revature.data.AdventurerDao;

import reactor.core.publisher.Mono;

@Service
public class AdventurerServiceImpl implements AdventurerService {
	AdventurerDao advDao;
	
	@Autowired
	public AdventurerServiceImpl(AdventurerDao advDao) {
		super();
		this.advDao = advDao;
	}


	// get character
	// input: name
	@Override
	public Mono<Adventurer> getCharacter(String name) {
		return advDao.findByCharacterName(name);
	}
	
	// add health
	// input: name, integer
	@Override
	public Mono<Adventurer> addHealth(String name, Integer value) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setCurrentHitPoints( adv.getCurrentHitPoints() + value);
			return advDao.save(adv);
		});
	}
	
	// remove health
	// input: name, integer
	@Override
	public Mono<Adventurer> removeHealth(String name, Integer value) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setCurrentHitPoints( adv.getCurrentHitPoints() - value);
			return advDao.save(adv);
		});
	}
	
	// restore health to max
	// input: name
	@Override
	public Mono<Adventurer> restoreHealth(String name) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setCurrentHitPoints( adv.getMaxHitPoints() );
			return advDao.save(adv);
		});
	}
	
	// set max health
	// input: name, integer
	@Override
	public Mono<Adventurer> setMaxHealth(String name, Integer value) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setMaxHitPoints(value);
			return advDao.save(adv);
		});
	}
	
	
	// reset max health
	// input: name
	@Override
	public Mono<Adventurer> resetMaxHealth(String name) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setMaxHitPoints(adv.getConstitution() + 5);
			return advDao.save(adv);
		});
	}
	
	// set trait stats
	// input: name, adventurer object with traits
	@Override
	public Mono<Adventurer> setTraits(String name, Adventurer advent) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setStrength(advent.getStrength());
			adv.setConstitution(advent.getConstitution());
			adv.setIntelligence(advent.getIntelligence());
			adv.setWisdom(advent.getWisdom());
			adv.setCharisma(advent.getCharisma());
			adv.setDexterity(advent.getDexterity());
			return advDao.save(adv);
		});
	}
	
	// set speed
	// input: name, integer
	@Override
	public Mono<Adventurer> setSpeed(String name, Integer value) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setSpeed(value);
			return advDao.save(adv);
		});
	}
	
	// reset speed
	// input: name
	@Override
	public Mono<Adventurer> setSpeed(String name) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setSpeed(adv.getDexterity() + 5);
			return advDao.save(adv);
		});
	}
	
	// set initiative
	// input: name, integer
	@Override
	public Mono<Adventurer> setInitiative(String name, Integer value) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setInitiative(value);
			return advDao.save(adv);
		});
	}
	
	// set armor class
	// input: name, integer
	@Override
	public Mono<Adventurer> setArmorClass(String name, Integer value) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setArmorClass(value);
			return advDao.save(adv);
		});
	}
	
	// create character*
	// input: adventurer object
	@Override
	public Mono<Adventurer> createWithName(Adventurer adv) {
		Adventurer advent = new Adventurer();
		// user submitted ones
		advent.setCharacterName(adv.getCharacterName());
		
		// empty ones
		advent.setStrength(null);
		advent.setConstitution(null);
		advent.setIntelligence(null);
		advent.setWisdom(null);
		advent.setCharisma(null);
		advent.setDexterity(null);
		
		advent.setMaxHitPoints(null);
		advent.setCurrentHitPoints(null);
		advent.setSpeed(null);
		advent.setArmorClass(null);
		advent.setInitiative(null);
		
		return advDao.save(advent);
	}
	
	// create character*
	// input: adventurer object
	@Override
	public Mono<Adventurer> createWithNameTraits(Adventurer adv) {
		Adventurer advent = new Adventurer();
		// user submitted ones
		advent.setCharacterName(adv.getCharacterName());
		advent.setStrength(adv.getStrength());
		advent.setConstitution(adv.getConstitution());
		advent.setIntelligence(adv.getIntelligence());
		advent.setWisdom(adv.getWisdom());
		advent.setCharisma(adv.getCharisma());
		advent.setDexterity(adv.getDexterity());
		
		// math ones
		advent.setMaxHitPoints(adv.getConstitution() + 5);
		advent.setCurrentHitPoints(adv.getConstitution() + 5);
		advent.setSpeed(adv.getDexterity() + 25);
		
		// empty ones
		advent.setArmorClass(null);
		advent.setInitiative(null);
		
		return advDao.save(advent);
	}
	
	// create character*
	// input: adventurer object
	@Override
	public Mono<Adventurer> createWithNameArmor(Adventurer adv) {
		Adventurer advent = new Adventurer();
		// user submitted ones
		advent.setCharacterName(adv.getCharacterName());
		advent.setArmorClass(adv.getArmorClass());
		
		// empty ones
		advent.setStrength(null);
		advent.setConstitution(null);
		advent.setIntelligence(null);
		advent.setWisdom(null);
		advent.setCharisma(null);
		advent.setDexterity(null);
		
		advent.setMaxHitPoints(null);
		advent.setCurrentHitPoints(null);
		advent.setSpeed(null);
		advent.setInitiative(null);
		
		return advDao.save(advent);
	}
	
	// create character*
	// input: adventurer object
	@Override
	public Mono<Adventurer> createWithNameTraitsArmor(Adventurer adv) {
		Adventurer advent = new Adventurer();
		// user submitted ones
		advent.setCharacterName(adv.getCharacterName());
		advent.setStrength(adv.getStrength());
		advent.setConstitution(adv.getConstitution());
		advent.setIntelligence(adv.getIntelligence());
		advent.setWisdom(adv.getWisdom());
		advent.setCharisma(adv.getCharisma());
		advent.setDexterity(adv.getDexterity());
		advent.setArmorClass(adv.getArmorClass());
		
		// math ones
		advent.setMaxHitPoints(adv.getConstitution() + 5);
		advent.setCurrentHitPoints(adv.getConstitution() + 5);
		advent.setSpeed(adv.getDexterity() + 25);
		
		// empty ones
		advent.setInitiative(null);
		
		return advDao.save(advent);
	}
	

}
