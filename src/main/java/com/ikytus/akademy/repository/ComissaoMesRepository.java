package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.ComissaoMes;

@Repository
public interface ComissaoMesRepository extends MongoRepository<ComissaoMes, String> {
			
	@Query("{'ano': ?0, 'mes': ?1 }")
	List<ComissaoMes>findAllByAnoMes (int ano, int mes,  Sort sort);
		
}
