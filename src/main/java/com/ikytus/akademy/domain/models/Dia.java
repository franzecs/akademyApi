package com.ikytus.akademy.domain.models;

import java.io.Serializable;

public class Dia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int dia;
	private String diaStr;
	private int sem;
	private String diaSemana;
	
	public Dia() {}

	public Dia(int dia, int sem) {
		super();
		this.dia = dia;
		this.diaStr = String.valueOf(dia);
		this.sem = sem;
		this.diaSemana = stringDiaSemana(sem);
	}
	
	private String stringDiaSemana(int dia) {
		if(dia == 1) {
			return "Dom";
		}
		if(dia == 2) {
			return "Seg";
		}
		if(dia == 3) {
			return "Ter";
		}
		if(dia == 4) {
			return "Qua";
		}
		if(dia == 5) {
			return "Qui";
		}
		if(dia == 6) {
			return "Sex";
		}
		if(dia == 7) {
			return "Sab";
		}else {
			return "I";
		}
		
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

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	

}
