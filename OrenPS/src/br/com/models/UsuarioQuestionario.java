/**
 * 
 */
package br.com.models;

import javax.persistence.Column;
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
@Table(name = "usuarios_questionarios")
public class UsuarioQuestionario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuarios_id", referencedColumnName = "id")
	private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name="questionarios_id", referencedColumnName = "id")
	private Questionario questionario;
	@Column(name ="email_enviado")
	private Boolean emailEnviado = false;
	@Column(name ="questionario_respondido")
	private Boolean questionarioRespondido = false;
	

	/**
	 * 
	 */
	public UsuarioQuestionario() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


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
	 * @return the emailEnviado
	 */
	public Boolean getEmailEnviado() {
		return emailEnviado;
	}


	/**
	 * @param emailEnviado the emailEnviado to set
	 */
	public void setEmailEnviado(Boolean emailEnviado) {
		this.emailEnviado = emailEnviado;
	}


	/**
	 * @return the questionarioRespondido
	 */
	public Boolean getQuestionarioRespondido() {
		return questionarioRespondido;
	}


	/**
	 * @param questionarioRespondido the questionarioRespondido to set
	 */
	public void setQuestionarioRespondido(Boolean questionarioRespondido) {
		this.questionarioRespondido = questionarioRespondido;
	}

}
