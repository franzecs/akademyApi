package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.Plano;
import com.ikytus.akademy.repository.PlanoRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class PlanoService {
	
	@Autowired
	private PlanoRepository planoRepository;
			
	public Page<Plano> findAll(int page, int count){
		return planoRepository.findAll(this.pages(page, count));
	}
	
	public Page<Plano> findByEmpresa(int page, int count, String empresaId) {
		return this.planoRepository.findByEmpresaId(this.pages(page, count), empresaId);
	}
	
	public List<Plano> listByEmpresa(String empresaId) {
		return this.planoRepository.findByEmpresaId(empresaId);
	}
			
	public Plano findById(String id) {
		Optional<Plano> plano = planoRepository.findById(id);
		return plano.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
		
	public Plano createOrUpdate(Plano plano) {
		return this.planoRepository.save(plano);
	}
		
	public void delete(String id) {
		findById(id);
		planoRepository.deleteById(id);
	}
				
	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}
