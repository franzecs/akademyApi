package com.ikytus.akademy.dto;

import java.io.Serializable;

import com.ikytus.akademy.domain.User;

public class InstrutorDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String email;
	
	public InstrutorDTO() {
	}

	public InstrutorDTO(User instrutor) {
		this.id = instrutor.getId();
		this.nome = instrutor.getNome();
		this.email = instrutor.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
