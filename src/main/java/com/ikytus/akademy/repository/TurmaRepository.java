package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.Turma;
import com.ikytus.akademy.domain.enums.DiaEnum;

@Repository
public interface TurmaRepository extends MongoRepository<Turma, String> {
			
	Page<Turma> findByEmpresaIdOrderByDia (Pageable pages, String empresaId);
	
	List<Turma> findByEmpresaIdOrderByDiaAscHorarioAsc (String empresaId);
	
	List<Turma> findByEmpresaIdAndDiaOrderByHorarioAsc(String empresaId, DiaEnum dia);
	
	Page<Turma> findByInstrutorIdOrderByDia (Pageable pages, String instrutorId);
	
	List<Turma> findByInstrutorIdOrderByDia (String instrutorId);
	
	List<Turma> findByDia(DiaEnum dia);
	
}
