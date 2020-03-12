package com.ikytus.akademy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ikytus.akademy.domain.enums.Perfil;
import com.ikytus.akademy.domain.models.Endereco;
import com.ikytus.akademy.dto.EmpresaDTO;
import com.ikytus.akademy.dto.PlanoDTO;

@Document
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotBlank(message = "Nome obrigatório")
	private String nome;
	
	@Indexed(unique = true)
	@NotBlank(message = "Email obrigatório")
	@Email(message = "Email invalid")
	private String email;
		
	@NotBlank(message = "Password obrigatório")
	@Size(min = 6)
	private String senha;
		
	private String tipoUser;
	private EmpresaDTO empresa;
	private Set<Integer> perfis = new HashSet<>();
	private boolean ativo;
	
	private String telefone;
	private String celular;
	private Endereco endereco;
	private String rg;
	private String cpf;	
	
	@DateTimeFormat(iso= ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	private String url_perfil;
    
    @DBRef(db="akademy",lazy=true)
	private List<Turma> turmas = new ArrayList<>();
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;
	
    private String sexo;
	private String peso;
	private String diaPagamento;
	private PlanoDTO plano;
	
	@DBRef
	private Plano plainer;
	private String obs;
	
	private double comissao;
	
	@Transient
	private String dtr;
			
    
	public User() {
	}
	
	public User(String id, String nome, Date dataCadastro, Date dataNascimento, String email, String senha, 
			String telefone, String celular, boolean ativo, Endereco endereco,String tipoUser, 
			EmpresaDTO empresa, String url_perfil, String rg, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.celular = celular;
		this.ativo = ativo;
		this.endereco = endereco;
		this.tipoUser = tipoUser;
		this.empresa = empresa;
		this.url_perfil = url_perfil;
		this.rg = rg;
		this.cpf = cpf;
		addPerfil(Perfil.USER);
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfis(){
		return  perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getTipoUser() {
		return tipoUser;
	}

	public void setTipoUser(String tipoUser) {
		this.tipoUser = tipoUser;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public String getUrl_perfil() {
		return url_perfil;
	}

	public void setUrl_perfil(String url_perfil) {
		this.url_perfil = url_perfil;
	}
	

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
		
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getDiaPagamento() {
		return diaPagamento;
	}

	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = diaPagamento;
	}

	public PlanoDTO getPlano() {
		return plano;
	}

	public void setPlano(PlanoDTO plano) {
		this.plano = plano;
	}
	
	public Plano getPlainer() {
		return plainer;
	}

	public void setPlainer(Plano plainer) {
		this.plainer = plainer;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getDtr() {
		return dtr;
	}

	public void setDtr(String dtr) {
		this.dtr = dtr;
	}
	
	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipoUser=" + tipoUser
				+ ", empresa=" + empresa + ", perfis=" + perfis + ", isAtivo=" + ativo + ", telefone=" + telefone
				+ ", celular=" + celular + ", endereco=" + endereco + ", rg=" + rg + ", cpf=" + cpf
				+ ", dataNascimento=" + dataNascimento + ", url_perfil=" + url_perfil + ", turmas=" + turmas
				+ ", dataCadastro=" + dataCadastro + ", sexo=" + sexo + ", peso=" + peso + ", diaPagamento="
				+ diaPagamento + ", plano=" + plano + ", obs=" + obs + "]";
	}
	
	
}