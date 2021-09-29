package com.revature.services;

import com.revature.beans.Adventurer;

import reactor.core.publisher.Mono;

public interface AdventurerService {

	Mono<Adventurer> getAdventurer(String name);

	Mono<Adventurer> addHealth(String name, Integer value);

	Mono<Adventurer> removeHealth(String name, Integer value);

	Mono<Adventurer> restoreHealth(String name);

	Mono<Adventurer> setMaxHealth(String name, Integer value);

	Mono<Adventurer> resetMaxHealth(String name);

	Mono<Adventurer> setTraits(String name, Adventurer advent);

	Mono<Adventurer> setSpeed(String name, Integer value);

	Mono<Adventurer> resetSpeed(String name);

	Mono<Adventurer> setInitiative(String name, Integer value);

	Mono<Adventurer> setArmorClass(String name, Integer value);
	
	Mono<Adventurer> routeCreate(Adventurer adv);

	Mono<Adventurer> createWithName(Adventurer adv);

	Mono<Adventurer> createWithNameTraits(Adventurer adv);

	Mono<Adventurer> createWithNameArmor(Adventurer adv);

	Mono<Adventurer> createWithNameTraitsArmor(Adventurer adv);
	
	Mono<Void> deleteAdventurer(String name);

	

}
