package com.ikytus.akademy.domain.enums;

public enum TipoEmpresaEnum {
	
	MATRIZ(1, "ADMINISTRADOR DO SISTEMA"),
	FILIAL(2, "ADMINISTRADOR DA EMPRESA"),
	FORNECEDOR(3, "GERENTE DA UNIDADE");
			
	private int cod;
	private String descricao;
	
	private TipoEmpresaEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoEmpresaEnum toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (TipoEmpresaEnum x : TipoEmpresaEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
