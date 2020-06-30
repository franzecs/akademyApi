package com.ikytus.akademy.domain.models;

import java.io.Serializable;

public class InstrutorBreaf implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	private String id;
	private String nome;
	private double comissao;
	private String type;
	
	
	public InstrutorBreaf() {
	}
	
	public InstrutorBreaf(String id, String nome, double comissao, String type) {
		this.id = id;
		this.nome = nome;
		this.comissao = comissao;
		this.type = type;
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

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstrutorBreaf other = (InstrutorBreaf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
