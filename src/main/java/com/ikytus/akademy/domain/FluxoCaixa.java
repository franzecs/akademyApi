package com.ikytus.akademy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.akademy.dto.EmpresaDTO;

@Document
public class FluxoCaixa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@DBRef(lazy=false)
	private EmpresaDTO empresa;
	
	private int mes;
	private int ano;
	private Double previsto;
	private Double executado;
	
	private List<ItemFluxoCaixa> itens = new ArrayList<>();
	
		
	public FluxoCaixa() {}
		
	
	public FluxoCaixa(String id, EmpresaDTO empresa, int mes, int ano, Double previsto, Double executado) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.mes = mes;
		this.ano = ano;
		this.previsto = previsto;
		this.executado = executado;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public Double getPrevisto() {
		return previsto;
	}


	public void setPrevisto(Double previsto) {
		this.previsto = previsto;
	}


	public Double getExecutado() {
		return executado;
	}


	public void setExecutado(Double executado) {
		this.executado = executado;
	}


	public List<ItemFluxoCaixa> getItens() {
		return itens;
	}


	public void setItens(List<ItemFluxoCaixa> itens) {
		this.itens = itens;
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
		FluxoCaixa other = (FluxoCaixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
