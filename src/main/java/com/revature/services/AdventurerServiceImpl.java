package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Adventurer;
import com.revature.data.AdventurerDao;

import reactor.core.publisher.Flux;
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
	public Mono<Adventurer> getAdventurer(String name) {
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
	public Mono<Adventurer> resetSpeed(String name) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setSpeed(adv.getDexterity() + 25);
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
			System.out.println(adv);
			return advDao.save(adv);
		});
	}
	
	// route to correct "create character" choice
	// input: adventurer object
	@Override
	public Mono<Adventurer> routeCreate(Adventurer adv) {
		// if both armor class and traits are empty: createWithName
		// if armor class full, traits empty: createWithNameArmor
		// if armor class empty, traits full: creatWithNameTraits
		// if armor class and traits are full: createWithNameTraitsArmor
		
		if(adv.getArmorClass() != null) {
			// armor class is full
			if(adv.getStrength() != null
					&& adv.getConstitution() != null
					&& adv.getIntelligence() != null
					&& adv.getWisdom() != null
					&& adv.getCharisma() != null
					&& adv.getDexterity() != null) {
				// traits are full
				
				// ac full, traits full
				return createWithNameTraitsArmor(adv);
			} else {
				// traits are empty
				
				// ac full, traits empty
				return createWithNameArmor(adv);
			}
			
		} else {
			// armor class is empty
			if(adv.getStrength() != null
					&& adv.getConstitution() != null
					&& adv.getIntelligence() != null
					&& adv.getWisdom() != null
					&& adv.getCharisma() != null
					&& adv.getDexterity() != null) {
				// traits are full
				
				// traits full, ac empty
				return createWithNameTraits(adv);
				
			} else {
				// traits are empty
				
				// traits are empty, ac empty
				return createWithName(adv);
			}
		}
		
		
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
