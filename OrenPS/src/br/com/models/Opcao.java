package br.com.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcleonio
 */
@Entity
@Table(name = "opcoes")
public class Opcao implements Serializable {

	private static final long serialVersionUID = 3598147865115425993L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="questao_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = false)
	private Questao questao;
	private String campo;

	public Opcao() {
		this.campo = "";
	}

	public Opcao(String campo) {
		this.campo = campo;
	}

	public Opcao(Questao questao) {
		this.questao = questao;
	}

	public String getCampo() {
		return this.campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String toString()
	{
		return this.campo;
	}
}
