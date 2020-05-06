package com.ikytus.akademy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.akademy.domain.models.InstrutorBreaf;

@Document
public class ComissaoMes implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	private String id;
	private Integer ano;
	private Integer mes;
	private List<InstrutorBreaf> instrutores = new ArrayList<>();
				
	public ComissaoMes() {}

	public ComissaoMes(String id, Integer ano, Integer mes) {
		this.id = id;
		this.ano = ano;
		this.mes = mes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public List<InstrutorBreaf> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<InstrutorBreaf> instrutores) {
		this.instrutores = instrutores;
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
		ComissaoMes other = (ComissaoMes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
