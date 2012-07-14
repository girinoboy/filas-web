package br.com.tables;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean(name = "Filial")
@Entity
@Table(name = "Filial")
public class Filial {

	@Id
	private Long id;
	private String nome;
	private String endereco;
	private Boolean ativoInativo;
	private String cnpj;
	private String descricao;
	

	public Filial() {}

	@Id
	@GeneratedValue
	@Column(name="usuario_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String login) {
		this.endereco = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(name="ativo_inativo", nullable=false)
	public Boolean getAtivoInativo() {
		return ativoInativo;
	}

	public void setAtivoInativo(Boolean ativoInativo) {
		this.ativoInativo = ativoInativo;
	}

	@Column(name="cnpj", nullable=false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cpf) {
		this.cnpj = cpf;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String observacao) {
		this.descricao = observacao;
	}

}
