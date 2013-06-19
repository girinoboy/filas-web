/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="usuario")
public class UsuarioDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Integer id;
	@Column(length=15, columnDefinition = "char(15)")
	private String login;
	@Column(length=10, columnDefinition = "char(10)")
	private String senha;
	private String nome;
	@Column(name="data_nascimento",columnDefinition="date")
	private Date dataNascimento;
	private String cpf;
	private String email;
	private String tema;
	
	/**
	 * 
	 */
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioDTO(String nome, int i) {
		this.id = i;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * @param tema the tema to set
	 */
	public void setTema(String tema) {
		this.tema = tema;
	} 

}
