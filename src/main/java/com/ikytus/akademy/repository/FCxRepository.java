package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.FCx;

@Repository
public interface FCxRepository extends MongoRepository<FCx, String> {
				
	List<FCx> findByAnoAndMesOrderByAnoAscMesAscDescricaoAsc (int ano, int mes);
	
}
