package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.ComissaoMes;
import com.ikytus.akademy.repository.ComissaoMesRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class ComissaoMesService {

	@Autowired
	private ComissaoMesRepository comissaoMesRepository;
		
	public ComissaoMes createOrUpdate(ComissaoMes comissaoMes) {
		return this.comissaoMesRepository.save(comissaoMes);
	}

	public List<ComissaoMes> findAll(int ano, int mes, Sort sort) {
		return comissaoMesRepository.findAllByAnoMes(ano, mes, sort);
	}
		
	public ComissaoMes findById(String id) {
		Optional<ComissaoMes> comissaoMes = comissaoMesRepository.findById(id);
		return comissaoMes.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public void delete(String id) {
		findById(id);
		comissaoMesRepository.deleteById(id);
	}
}
