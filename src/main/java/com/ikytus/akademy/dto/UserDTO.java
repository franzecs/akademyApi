package com.ikytus.akademy.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.domain.Plano;
import com.ikytus.akademy.domain.enums.Perfil;
import com.ikytus.akademy.domain.models.Endereco;

public class UserDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String email;
	private Set<Integer> perfis = new HashSet<>();
        private boolean isAtivo;
	private Endereco endereco;
	private Plano plano;
        private EmpresaDTO empresa;
        private String url_perfil;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.isAtivo = user.isAtivo();
		this.endereco = user.getEndereco();
		this.empresa = user.getEmpresa();
		this.plano = user.getPlainer();
		this.url_perfil = user.getUrl_perfil();
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
	
	public Set<Perfil> getPerfis(){
		return  perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}
	
	public PlanoDTO getPlano() {
		return plano;
	}

	public void setPlano(PlanoDTO plano) {
		this.plano = plano;
	}

	public String getUrl_perfil() {
		return url_perfil;
	}

	public void setUrl_perfil(String url_perfil) {
		this.url_perfil = url_perfil;
	}
}
