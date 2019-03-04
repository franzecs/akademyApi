package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.FluxoCaixa;
import com.ikytus.akademy.domain.Turma;

@Repository
public interface FluxoCaixaRepository extends MongoRepository<FluxoCaixa, String> {
	
	Page<FluxoCaixa> findByEmpresaIdAndAnoOrderByMesAsc (Pageable pages, String empresaId,  int ano);
	
	List<FluxoCaixa> findByEmpresaIdAndAnoAndMesOrderByAnoAscMesAsc (String empresaId, int ano, int mes);
	
	List<Turma> findByEmpresaIdAndMesOrderByMesAsc(String empresaId, int mes);
	
}
