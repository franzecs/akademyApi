package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.ItemFluxoCaixa;
import com.ikytus.akademy.repository.ItemFluxoCaixaRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class ItemFluxoCaixaService {

	@Autowired
	private ItemFluxoCaixaRepository itemFluxoCaixaRepository;

	public ItemFluxoCaixa createOrUpdate(ItemFluxoCaixa itemFluxo) {
		
		return this.itemFluxoCaixaRepository.save(itemFluxo);
	}

	public ItemFluxoCaixa findById(String id, String empresaId) {
		Optional<ItemFluxoCaixa> itemFluxo = itemFluxoCaixaRepository.findById(id);
		
		return itemFluxo.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Page<ItemFluxoCaixa> findByFluxo(int page, int count, String empresaId, String fluxoId) {

		Page<ItemFluxoCaixa> itensFluxo = this.itemFluxoCaixaRepository.
					findByEmpresaIdAndFluxoCaixaIdOrderByDiaAsc(this.pages(page, count), empresaId, fluxoId);
		
		return itensFluxo;
	}

	public void delete(String id, String empresaId) {
		findById(id, empresaId);
		itemFluxoCaixaRepository.deleteById(id);
	}
	
	public List<ItemFluxoCaixa> listDespesasFixas(String empresaId){
		return this.itemFluxoCaixaRepository.findByEmpresaIdAndTipoOrderByDescricaoAsc(empresaId, 4);
	}

	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}