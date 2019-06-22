package com.ikytus.akademy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.akademy.dto.EmpresaDTO;
import com.ikytus.akademy.dto.InstrutorDTO;

@Document
public class HorarioMes implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	private String id;
	private Integer ano;
	private Integer mes;
	private Integer dia;
	private Integer semana;
	private String horario;
	private EmpresaDTO empresa;
	private InstrutorDTO instrutor;

	//@DBRef(db="akademy",lazy=false)
	private List<InstrutorDTO> alunos = new ArrayList<>();
			
	public HorarioMes() {}

	public HorarioMes(String id, Integer ano, Integer mes, Integer dia, Integer semana, String horario, EmpresaDTO empresa, InstrutorDTO instrutor) {
		this.id = id;
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
		this.semana = semana;
		this.horario = horario;
		this.empresa = empresa;
		this.instrutor = instrutor;
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
		
	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getSemana() {
		return semana;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
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
	
	public List<InstrutorDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<InstrutorDTO> alunos) {
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
		HorarioMes other = (HorarioMes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
