package com.revature.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.AdventurerDTO;

import reactor.core.publisher.Mono;

@Repository
public interface AdventurerDao extends ReactiveCassandraRepository<AdventurerDTO, String> {
	Mono<AdventurerDTO> findByCharacterName(String name);

}
