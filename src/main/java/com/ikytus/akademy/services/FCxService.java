package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.FCx;
import com.ikytus.akademy.repository.FCxRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class FCxService {

	@Autowired
	private FCxRepository fCxRepository;
	
	public FCx createOrUpdate(FCx fluxo) {

		if (fluxo.getId() != null) {
			FCx fCxDB = findById(fluxo.getId());
			BeanUtils.copyProperties(fluxo, fCxDB, "id");
			return this.fCxRepository.save(fCxDB);
		} else {
			return this.fCxRepository.save(fluxo);
		}
	}

	public FCx findById(String id) {
		Optional<FCx> fluxo = fCxRepository.findById(id);
		return fluxo.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<FCx> findBy(int ano, int mes) {
		List<FCx> fluxos = fCxRepository.findByAnoAndMesOrderByAnoAscMesAscDescricaoAsc(ano, mes);
		return fluxos;
	}
	
	public List<FCx> findAll (){
		return this.fCxRepository.findAll();
	}

	public void delete(String id) {
		findById(id);
		fCxRepository.deleteById(id);
	}

	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}