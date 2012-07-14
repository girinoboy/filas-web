package br.com.tables;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean(name = "Usuario")
@Entity
@Table(name = "Usuario")
public class Usuario {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String sexo;
	private Date dataNascimento;
	private Boolean ativoInativo;
	private String estadoCivil;
	private String cpf;
	private String rg;
	//private String naturaliadeUF;
	//private String naturalidade;
	private String nomeMae;
	private String nomePai;
	private String observacao;
	

	public Usuario() {}

	@Id
	@GeneratedValue
	@Column(name="usuario_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Column(name="data_nascimento", nullable=false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(name="ativo_inativo", nullable=false)
	public Boolean getAtivoInativo() {
		return ativoInativo;
	}

	public void setAtivoInativo(Boolean ativoInativo) {
		this.ativoInativo = ativoInativo;
	}
	@Column(name="estado_civil")
	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	@Column(name="cpf", nullable=false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	@Column(name="nome_mae")
	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	@Column(name="nome_pai")
	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
