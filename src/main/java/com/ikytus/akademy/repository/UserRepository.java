package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmail(String email);
	
	Page<User> findBytipoUserAndEmpresaIdOrderByNome(Pageable pages, String tipo, String empresaId);
	
	Page<User> findBytipoUserAndEmpresaIdAndAtivoAndNomeContainingIgnoreCaseOrderByNome(Pageable pages, String tipo, String empresaId, boolean ativo, String nome);
	
	Page<User> findBytipoUserAndEmpresaIdAndNomeContainingIgnoreCase(Pageable pages, String tipo, String empresaId,String nome);
	
	List<User> findBytipoUserAndEmpresaId(String tipo, String empresaId);
	
	List<User> findBytipoUserAndEmpresaIdAndAtivoOrderByNome(String tipo, String empresaId, boolean ativo);
	
	@Query("{ 'empresa.id': ?0, 'ano': ?1, 'mes': ?2 }")
	List<User> findAllByEmpresa (String empresaId, int ano, int mes, Sort sort);
}
