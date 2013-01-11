/**
 * 
 */
package br.com.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name = "respostas")
public class Resposta {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuarios_id", referencedColumnName = "id")
	private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name="questionarios_id", referencedColumnName = "id")
	private Questionario questionario;
    @ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name="opcoes_id", referencedColumnName = "id")
	private Opcao opcao;
	private String resposta;
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		return questionario;
	}
	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	/**
	 * @return the opcao
	 */
	public Opcao getOpcao() {
		return opcao;
	}
	/**
	 * @param opcao the opcao to set
	 */
	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}
	/**
	 * @return the resposta
	 */
	public String getResposta() {
		return resposta;
	}
	/**
	 * @param resposta the resposta to set
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

}
