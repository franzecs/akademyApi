package com.ikytus.akademy.domain.enums;

public enum DiaEnum {
	SEGUNDA(1,"SEGUNDA-FEIRA"),
	TERCA(2,"TERÇA-FEIRA"),
	QUARTA(3,"QUARTA-FEIRA"),
	QUINTA(4,"QUINTA-FEIRA"),
	SEXTA(5,"SEXTA-FEIRA"),
	SABADO(6,"SÁBADO");
	
	private int cod;
	private String descricao;
	
	private DiaEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static DiaEnum toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (DiaEnum x : DiaEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}