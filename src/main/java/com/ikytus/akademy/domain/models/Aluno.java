package com.ikytus.akademy.domain.models;

import java.io.Serializable;
import java.util.Date;

import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.dto.EmpresaDTO;

public class Aluno extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Aluno() {
	}
	
	public Aluno(String id, String nome, Date dataCadastro, Date dataNascimento, String email, String senha, 
			String telefone, String celular, boolean isAtivo, Endereco endereco,String tipoUser, 
			EmpresaDTO empresa, String url_perfil, String rg, String cpf) {
		super(id,nome,dataCadastro,dataNascimento,email,senha,telefone,celular,isAtivo,endereco,tipoUser,empresa,url_perfil,rg,cpf);
	}
}