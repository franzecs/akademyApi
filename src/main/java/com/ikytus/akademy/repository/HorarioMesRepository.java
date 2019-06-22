package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.HorarioMes;

@Repository
public interface HorarioMesRepository extends MongoRepository<HorarioMes, String> {
		
	@Query("{ 'empresa.id': ?0, 'ano': ?1, 'mes': ?2 }")
	List<HorarioMes> findAllByEmpresa (String empresaId, int ano, int mes, Sort sort);
	
	@Query("{ 'empresa.id': ?0, 'ano': ?1, 'mes': ?2, 'instrutor.id': ?3 }")
	List<HorarioMes>findAllByEmpresaAndInstrutor (String empresaId, int ano, int mes, String instrutorId, Sort sort);
		
}
