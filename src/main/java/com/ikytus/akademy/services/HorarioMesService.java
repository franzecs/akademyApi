package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.HorarioMes;
import com.ikytus.akademy.repository.HorarioMesRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class HorarioMesService {

	@Autowired
	private HorarioMesRepository horarioMesRepository;
		
	public HorarioMes createOrUpdate(HorarioMes horarioMes) {
		return this.horarioMesRepository.save(horarioMes);
	}

	public List<HorarioMes> findAll(String empresaId, int ano, int mes, Sort sort) {
		return horarioMesRepository.findAllByEmpresa(empresaId, ano, mes, sort);
	}
	
	public List<HorarioMes> findAllbyInstrutor(String empresaId, int ano, int mes, String instrutorId, Sort sort) {
		return horarioMesRepository.findAllByEmpresaAndInstrutor(empresaId, ano, mes, instrutorId, sort);
	}
	
	public HorarioMes findById(String id) {
		Optional<HorarioMes> horarioMes = horarioMesRepository.findById(id);
		return horarioMes.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public void delete(String id) {
		findById(id);
		horarioMesRepository.deleteById(id);
	}

	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}
