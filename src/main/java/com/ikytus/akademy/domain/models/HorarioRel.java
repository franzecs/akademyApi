package com.ikytus.akademy.domain.models;

import java.io.Serializable;
import java.util.List;

import com.ikytus.akademy.domain.User;

public class HorarioRel extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	public String horario;
	public String segunda;
	public String terca;
	public String quarta;
	public String quinta;
	public String sexta;
	
	public HorarioRel() {
	}
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getSegunda() {
		return segunda;
	}

	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}

	public String getTerca() {
		return terca;
	}

	public void setTerca(String terca) {
		this.terca = terca;
	}

	public String getQuarta() {
		return quarta;
	}

	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}

	public String getQuinta() {
		return quinta;
	}

	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}

	public String getSexta() {
		return sexta;
	}

	public void setSexta(String sexta) {
		this.sexta = sexta;
	}
}