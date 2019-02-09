package com.ikytus.akademy.domain.models;

import java.io.Serializable;

public class Dia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int dia;
	private String diaStr;
	private int sem;
	
	public Dia() {}

	public Dia(int dia, int sem) {
		super();
		this.dia = dia;
		this.diaStr = String.valueOf(dia);
		this.sem = sem;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getDiaStr() {
		return diaStr;
	}

	public void setDiaStr(String diaStr) {
		this.diaStr = diaStr;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}

}
