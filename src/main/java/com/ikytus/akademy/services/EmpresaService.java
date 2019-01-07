package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.Empresa;
import com.ikytus.akademy.repository.EmpresaRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Page<Empresa> findAllPage(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return this.empresaRepository.findAll(pages);
	}
	
	public List<Empresa> findAll(){
		return this.empresaRepository.findAll();
	}
		
	public Empresa findById(String id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		return empresa.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Empresa createOrUpdate (Empresa empresa) {
		return empresaRepository.save(empresa);
	}
				
	public void delete(String id) {
		findById(id);
		empresaRepository.deleteById(id);
	}
		
	public Empresa update(Empresa empresa) {
		Empresa newEmpresa = findById(empresa.getId());
		updateData(newEmpresa, empresa);
		return empresaRepository.save(newEmpresa);
	}
		
	public void updateData(Empresa newEmpresa, Empresa empresa) {
		newEmpresa.setNome(empresa.getNome());
		newEmpresa.setEmail(empresa.getEmail());
		newEmpresa.setMatriz(empresa.getMatriz());
		newEmpresa.setEndereco(empresa.getEndereco());
	}
}
