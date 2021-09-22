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
	public Mono<Adventurer> getCharacter(String name) {
		return advDao.findByCharacterName(name).map(dto -> dto.getAdventurer());
	}
	
	// add health
	// input: name, integer
	public Mono<Adventurer> addHealth(String name, Integer addHealth) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setCurrentHitPoints( adv.getCurrentHitPoints() + addHealth);
			return advDao.save(adv);
		}).map(adv -> adv.getAdventurer());
	}
	
	// remove health
	// input: name, integer
	public Mono<Adventurer> removeHealth(String name, Integer remHealth) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setCurrentHitPoints( adv.getCurrentHitPoints() - remHealth);
			return advDao.save(adv);
		}).map(adv -> adv.getAdventurer());
	}
	
	// restore health to max
	// input: name
	public Mono<Adventurer> restoreHealth(String name) {
		return advDao.findByCharacterName(name).flatMap(adv -> {
			adv.setCurrentHitPoints( adv.getMaxHitPoints() );
			return advDao.save(adv);
		}).map(adv -> adv.getAdventurer());
	}
	
	// set trait stats
	// input: name, character object with traits
	
	// set speed
	// input: name, integer
	
	// set initiative
	// input: name, integer
	
	// set armor class
	// input: name, integer
	
	// create character*
	// input: name
	
	// create character*
	// input: name, traits
	
	// create character*
	// input: name, armor class
	
	// create character*
	// name, traits, armor class
	

}
