package com.ikytus.akademy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.FluxoCaixa;
import com.ikytus.akademy.domain.ItemFluxoCaixa;
import com.ikytus.akademy.repository.FluxoCaixaRepository;
import com.ikytus.akademy.repository.ItemFluxoCaixaRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class FluxoCaixaService {

	@Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;

	@Autowired
	private ItemFluxoCaixaRepository itemRepository;

	public FluxoCaixa createOrUpdate(FluxoCaixa fluxo) {
		return this.fluxoCaixaRepository.save(fluxo);
	}

	public FluxoCaixa findById(String id, String empresaId) {
		Optional<FluxoCaixa> fluxo = fluxoCaixaRepository.findById(id);

		//List<ItemFluxoCaixa> itens = itemRepository.findByEmpresaIdAndFluxoCaixaIdOrderByDescricaoAsc(empresaId, id);
		List<ItemFluxoCaixa> itens = itemRepository.findByFluxoCaixaIdOrderByDescricaoAsc(id);
		fluxo.get().getItens().addAll(itens);
		return fluxo.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Page<FluxoCaixa> findByEmpresa(int page, int count, String empresaId, int ano) {
		Page<FluxoCaixa> fluxos = this.fluxoCaixaRepository.findByEmpresaIdAndAnoOrderByMesAsc(this.pages(page, count),
				empresaId, ano);

		/*for (FluxoCaixa f : fluxos) {

			List<ItemFluxoCaixa> itens = itemRepository.findByEmpresaIdAndFluxoCaixaIdOrderByDescricaoAsc(empresaId,
					f.getId());
			f.getItens().addAll(itens);
		}*/
		return fluxos;
	}

	public void delete(String id, String empresaId) {
		findById(id, empresaId);
		fluxoCaixaRepository.deleteById(id);
	}

	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}
