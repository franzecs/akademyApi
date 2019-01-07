package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.Turma;

@Repository
public interface TurmaRepository extends MongoRepository<Turma, String> {
			
	Page<Turma> findByEmpresaIdOrderByDia (Pageable pages, String empresaId);
	
	List<Turma> findByEmpresaIdOrderByDia (String empresaId);
	
	Page<Turma> findByInstrutorIdOrderByDia (Pageable pages, String instrutorId);
	
	List<Turma> findByInstrutorIdOrderByDia (String instrutorId);
	
}
