package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Adventurer;

import reactor.core.publisher.Mono;

@Repository
public interface AdventurerDao extends ReactiveCassandraRepository<Adventurer, String> {
	Mono<Adventurer> findByCharacterName(String name);

}
