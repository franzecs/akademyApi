package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.ItemFluxoCaixa;

@Repository
public interface ItemFluxoCaixaRepository extends MongoRepository<ItemFluxoCaixa, String> {
	
	List<ItemFluxoCaixa> findByEmpresaIdOrderByDiaAsc(String empresaId);
	
	List<ItemFluxoCaixa> findByEmpresaIdAndTipoOrderByDescricaoAsc(String empresaId, Integer tipo);
			
	Page<ItemFluxoCaixa> findByEmpresaIdAndFluxoCaixaIdOrderByDiaAsc (Pageable pages, String empresaId, String fluxoCaixaId);
	
	List<ItemFluxoCaixa> findByEmpresaIdAndFluxoCaixaIdOrderByDiaAsc (String empresaId, String fluxoCaixaId);
	
	List<ItemFluxoCaixa> findByEmpresaIdAndFluxoCaixaIdOrderByDescricaoAsc (String empresaId, String fluxoCaixaId);
	
	Page<ItemFluxoCaixa> findByEmpresaIdAndFluxoCaixaIdAndDescricaoContainingAndStatusContainingOrderByDiaAsc (Pageable pages, String empresaId, String fluxoCaixaId, String descricao, String status);
			
}
