package com.ikytus.akademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.akademy.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmail(String email);
	
	Page<User> findBytipoUserAndEmpresaId(Pageable pages, String tipo, String empresaId);
	
	List<User> findBytipoUserAndEmpresaId(String tipo, String empresaId);
}
