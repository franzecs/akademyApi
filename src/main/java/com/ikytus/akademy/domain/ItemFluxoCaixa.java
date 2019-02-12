package com.ikytus.akademy.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ItemFluxoCaixa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private int mes;
	private String descricao;
	private Double valorEntrada;
	private Double valorSaida;
	
		
	public ItemFluxoCaixa() {}
		
	public ItemFluxoCaixa(String id, int mes, String descricao, Double valorEntrada, Double valorSaida) {
		super();
		this.id = id;
		this.mes = mes;
		this.descricao = descricao;
		this.valorEntrada = valorEntrada;
		this.valorSaida = valorSaida;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public Double getValorSaida() {
		return valorSaida;
	}

	public void setValorSaida(Double valorSaida) {
		this.valorSaida = valorSaida;
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
