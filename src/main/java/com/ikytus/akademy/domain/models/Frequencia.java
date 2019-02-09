package com.ikytus.akademy.domain.models;

import java.io.Serializable;

import com.ikytus.akademy.domain.enums.DiaEnum;

public class Frequencia implements Serializable{
	private static final long serialVersionUID = 1L;

	private String instrutor;
	private String horario;
	private DiaEnum semana;
	private int dia;
	private String aluno;
	
	public Frequencia(){}
	
	public Frequencia(String instrutor, String horario, DiaEnum diaSemana, String aluno) {
		super();
		this.instrutor = instrutor;
		this.horario = horario;
		this.semana = diaSemana;
		this.aluno = aluno;
		this.dia = intDia(diaSemana);
	}
	
	private int intDia(DiaEnum dia) {
		if(dia == DiaEnum.SEGUNDA) {
			return 2;
		}
		if(dia == DiaEnum.TERCA) {
			return 3;
		}
		if(dia == DiaEnum.QUARTA) {
			return 4;
		}
		if(dia == DiaEnum.QUINTA) {
			return 5;
		}
		if(dia == DiaEnum.SEXTA) {
			return 6;
		}
		if(dia == DiaEnum.SABADO) {
			return 7;
		}else {
		return 0;
		}
	}

	public String getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(String instrutor) {
		this.instrutor = instrutor;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public DiaEnum getSemana() {
		return semana;
	}

	public void setSemana(DiaEnum semana) {
		this.semana = semana;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
}
