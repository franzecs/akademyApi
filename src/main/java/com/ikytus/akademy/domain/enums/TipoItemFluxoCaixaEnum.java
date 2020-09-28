package com.ikytus.akademy.domain.enums;

public enum TipoItemFluxoCaixaEnum {
	ENTRADA(1,"Entrada"),
	SAIDA(2,"Saida"),
	PAGAMENTOPROFESSOR(3,"Pagamento Professor"),
	DESPESASFIXAS(4,"Despesas Fixas"),
	MENSALIDADE(5,"Mensalidade");
	
	private int cod;
	private String descricao;
	
	private TipoItemFluxoCaixaEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoItemFluxoCaixaEnum toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (TipoItemFluxoCaixaEnum x : TipoItemFluxoCaixaEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
