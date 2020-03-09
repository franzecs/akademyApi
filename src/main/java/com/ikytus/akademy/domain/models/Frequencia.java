package com.ikytus.akademy.domain.models;

import java.io.Serializable;

import com.ikytus.akademy.domain.enums.DiaEnum;

public class Frequencia implements Serializable{
	private static final long serialVersionUID = 1L;

	private String instrutor;
	private String horario;
	private DiaEnum semana;
	
	private DiaEnum semana2;
	private DiaEnum semana3;
	
	private int dia;
	private int dia2;
	private int dia3;
	private String aluno;
	private int idade;
	
	public Frequencia(){}
	
	public Frequencia(String instrutor, String horario, DiaEnum diaSemana, DiaEnum diaSemana2, DiaEnum diaSemana3, String aluno, int idade) {
		super();
		this.instrutor = instrutor;
		this.horario = horario;
		this.semana = diaSemana;
		this.aluno = aluno;
		this.dia = intDia(diaSemana);
		this.dia2 = intDia(diaSemana2);
		this.dia3 = intDia(diaSemana3);
		this.idade = idade;
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
	
	public DiaEnum getSemana() {
		return semana;
	}

	public void setSemana(DiaEnum semana) {
		this.dia = intDia(semana);
		this.semana = semana;
	}

	public DiaEnum getSemana2() {
		return semana2;
	}

	public void setSemana2(DiaEnum semana2) {
		this.dia2 = intDia(semana2);
		this.semana2 = semana2;
	}

	public DiaEnum getSemana3() {
		return semana3;
	}

	public void setSemana3(DiaEnum semana3) {
		this.dia3 = intDia(semana3);
		this.semana3 = semana3;
	}

	public int getDia2() {
		return dia2;
	}

	public void setDia2(int dia2) {
		this.dia2 = dia2;
	}

	public int getDia3() {
		return dia3;
	}

	public void setDia3(int dia3) {
		this.dia3 = dia3;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Frequencia [instrutor=" + instrutor + ", horario=" + horario + ", semana=" + semana + ", semana2="
				+ semana2 + ", semana3=" + semana3 + ", dia=" + dia + ", dia2=" + dia2 + ", dia3=" + dia3 + ", aluno="
				+ aluno + "]";
	}
	
	
	
}
