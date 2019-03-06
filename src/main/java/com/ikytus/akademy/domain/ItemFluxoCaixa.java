package com.ikytus.akademy.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.akademy.domain.enums.TipoItemFluxoCaixaEnum;
import com.ikytus.akademy.dto.EmpresaDTO;

@Document
public class ItemFluxoCaixa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@DBRef(lazy=false)
	private EmpresaDTO empresa;
	
	@DBRef
	private FluxoCaixa fluxoCaixa;
	
	private Integer tipo;
	private String dia;
	private String descricao;
	private Double valor;
	private String status;
			
	public ItemFluxoCaixa() {}
			
	public ItemFluxoCaixa(String id, EmpresaDTO empresa, FluxoCaixa fluxoCaixa, TipoItemFluxoCaixaEnum tipo, String dia,
			String descricao, Double valor, String status) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.fluxoCaixa = fluxoCaixa;
		this.dia = dia;
		this.tipo = (tipo==null)? null : tipo.getCod();
		this.descricao = descricao;
		this.valor = valor;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FluxoCaixa getFluxoCaixa() {
		return fluxoCaixa;
	}

	public void setFluxoCaixa(FluxoCaixa fluxoCaixa) {
		this.fluxoCaixa = fluxoCaixa;
	}
		
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public TipoItemFluxoCaixaEnum getTipo() {
		return TipoItemFluxoCaixaEnum.toEnum(tipo);
	}

	public void setTipo(TipoItemFluxoCaixaEnum tipo) {
		this.tipo = tipo.getCod();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemFluxoCaixa other = (ItemFluxoCaixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
