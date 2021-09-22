package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.CharacterDTO;

import reactor.core.publisher.Mono;

@Repository
public interface CharacterDao extends ReactiveCassandraRepository<CharacterDTO, String> {
	Mono<CharacterDTO> findByCharacterName(String name);

}
