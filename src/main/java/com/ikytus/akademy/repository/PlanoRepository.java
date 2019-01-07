package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.Plano;

@Repository
public interface PlanoRepository extends MongoRepository<Plano, String> {
			
	Page<Plano> findByEmpresaId (Pageable pages, String empresaId);
	List<Plano> findByEmpresaId (String empresaId);
	
}
