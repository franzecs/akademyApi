package com.ikytus.akademy.dto;

import java.io.Serializable;

import com.ikytus.akademy.domain.Plano;

public class PlanoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private Double valor;
	private String descricao;
	private String tipo;
	
	public PlanoDTO() {}
		
	public PlanoDTO(Plano plano) {
		this.id = plano.getId();
		this.nome = plano.getNome();
		this.valor = plano.getValor();
		this.descricao = plano.getDescricao();
		this.tipo = plano.getTipo();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
