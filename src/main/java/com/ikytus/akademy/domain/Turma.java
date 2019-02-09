package com.ikytus.akademy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.akademy.domain.enums.DiaEnum;
import com.ikytus.akademy.dto.EmpresaDTO;
import com.ikytus.akademy.dto.InstrutorDTO;

@Document
public class Turma implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	private String id;
	private Integer dia;
	private String horario;
	private String tipo;
	private EmpresaDTO empresa;
	
	private InstrutorDTO instrutor;

	@DBRef(db="akademy",lazy=true)
	private List<User> alunos = new ArrayList<>();
			
	public Turma() {}

	public Turma(String id, DiaEnum dia, String horario, String tipo, EmpresaDTO empresa, InstrutorDTO instrutor) {
		this.id = id;
		this.dia = (dia==null)? null : dia.getCod();
		this.horario = horario;
		this.tipo = tipo;
		this.empresa = empresa;
		this.instrutor = instrutor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public DiaEnum getDia() {
		return DiaEnum.toEnum(dia);
	}

	public void setDia(DiaEnum dia) {
		this.dia = dia.getCod();
	}
		
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public void setInstrutor(InstrutorDTO instrutor) {
		this.instrutor = instrutor;
	}

	public InstrutorDTO getInstrutor() {
		return instrutor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
			
	public List<User> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<User> alunos) {
		this.alunos = alunos;
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
