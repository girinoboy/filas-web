/** Classe JavaBean que serve de base para o ManagedBean AgendaContatoBean */

package br.com.agenda;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean(name = "contato")
@SessionScoped
@Entity
@Table(name = "USERS")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer	idUser;

	@Column(name = "nome")
	private String	nome;

	@Column(name = "sexo")
	private String	sexo;

	@Column(name = "endereco")
	private String	endereco;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

}
